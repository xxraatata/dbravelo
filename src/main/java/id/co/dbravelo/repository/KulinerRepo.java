package id.co.dbravelo.repository;

import id.co.dbravelo.model.Kuliner;
import id.co.dbravelo.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KulinerRepo extends JpaRepository<Kuliner, Integer> {
    @Query(value = "SELECT * FROM ms_kuliner WHERE status = 1", nativeQuery = true)
    List<Kuliner> findAllActive();

    @Query(value = "SELECT * FROM ms_kuliner WHERE status = 1 AND restoran_id = :restoID", nativeQuery = true)
    List<Kuliner> findByRestoranIdActive(int restoranId);

    @Query(value = "SELECT * FROM ms_kuliner WHERE status = 1 AND kuliner_id = :id", nativeQuery = true)
    List<Kuliner> findByIdActive(int kulinerId);
}
