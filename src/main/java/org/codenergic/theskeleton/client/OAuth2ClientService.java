package org.codenergic.theskeleton.client;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.ClientDetailsService;

public interface OAuth2ClientService extends ClientDetailsService {
	@PreAuthorize("isAuthenticated() or hasAuthority('client_delete')")
	void deleteClient(String id);

	Optional<OAuth2ClientEntity> findClientById(String id);

	@PreAuthorize("isAuthenticated() and #userId == principal.id")
	Page<OAuth2ClientEntity> findClientByOwner(String userId, Pageable pageable);

	@PreAuthorize("hasAuthority('client_read_all')")
	Page<OAuth2ClientEntity> findClients(String keyword, Pageable pageable);

	@PreAuthorize("isAuthenticated()")
	@PostAuthorize("returnObject.createdBy.userId == principal.id or hasAuthority('client_generate_secret')")
	OAuth2ClientEntity generateSecret(String clientId);

	@PreAuthorize("isAuthenticated()")
	OAuth2ClientEntity saveClient(OAuth2ClientEntity client);

	@PreAuthorize("isAuthenticated()")
	@PostAuthorize("returnObject.createdBy.userId == principal.id or hasAuthority('client_update')")
	OAuth2ClientEntity updateClient(String clientId, OAuth2ClientEntity entity);
}
