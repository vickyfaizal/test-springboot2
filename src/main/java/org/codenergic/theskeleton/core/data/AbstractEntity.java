/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codenergic.theskeleton.core.data;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class AbstractEntity implements Activeable, Identifiable<String>, Serializable {
	@Id
	@GeneratedValue(generator = "flake")
	@GenericGenerator(name = "flake", strategy = "com.sigma.columbus.api.core.data.FlakeIdGenerator")
	private String id;
	private int status = Activeable.Status.ACTIVE.getStatus();

	@Override
	public String getId() {
		return id;
	}

	public AbstractEntity setId(String id) {
		this.id = id;
		return this;
	}

	@Override
	public int getStatus() {
		return status;
	}

	public AbstractEntity setStatus(int status) {
		this.status = status;
		return this;
	}
}
