package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import tn.esprit.spring.entity.*;
@Repository
public interface AchatRepository extends CrudRepository<Achat, Long>{
	
	List<Achat> findByDatedachat(Date Datedachat);
	Achat findById(long l);
	void deleteAll();
	
	@Query("SELECT Remarque FROM Achat")
	public List<String> achatRemarques();
	//@Query("SELECT (MAX(a.id), 0) FROM Achat a")
//Long getMaxId();
}
