package com.hanbit.there.api.admin.repo;

import java.util.List;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.hanbit.there.api.domain.Activity;

public interface AdminActivityRepository extends CrudRepository<Activity, String> {

	@Query("SELECT META().id AS _ID, META().cas AS _CAS,"
			+ " name, photos, intro"
			+ " FROM #{#n1ql.bucket}"
			+ " WHERE thereId = $1 AND #{#n1ql.filter}"
			+ " ORDER BY name ASC")
	List<Activity> findByThereIdOrderByName(String thereId);

}
