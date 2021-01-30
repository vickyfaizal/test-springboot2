package org.codenergic.theskeleton.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2ClientRepository extends PagingAndSortingRepository<OAuth2ClientEntity, String> {
	Page<OAuth2ClientEntity> findByCreatedBy(String userId, Pageable pageable);

	@Query("FROM OAuth2ClientEntity o WHERE o.name LIKE %?1% OR o.description LIKE %?1%")
	Page<OAuth2ClientEntity> findByNameOrDescriptionContaining(String keyword, Pageable pageable);
}
