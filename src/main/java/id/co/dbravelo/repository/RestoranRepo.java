package id.co.dbravelo.repository;

import id.co.dbravelo.model.Restoran;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestoranRepo extends JpaRepository<Restoran, Integer> {
    @Query(value = "SELECT * FROM ms_restoran WHERE status = 1 ORDER BY created_at DESC;", nativeQuery = true)
    List<Restoran> findAllActive();

    @Query(value = "SELECT TOP 1 * FROM ms_restoran WHERE status = 1 AND restoran_id = :id", nativeQuery = true)
    Optional<Restoran> findByIdActive(int id);

    @Query(value = " SELECT *, \n" +
            "(6371 * acos( \n" +
            "    cos(radians(:lat)) * cos(radians(latitude)) * \n" +
            "    cos(radians(longitude) - radians(:lon)) + \n" +
            "    sin(radians(:lat)) * sin(radians(latitude)) \n" +
            ")) as distance \n" +
            "FROM ms_restoran \n" +
            "WHERE status = 1 \n" +
            "AND (6371 * acos( \n" +
            "    cos(radians(:lat)) * cos(radians(latitude)) * \n" +
            "    cos(radians(longitude) - radians(:lon)) + \n" +
            "    sin(radians(:lat)) * sin(radians(latitude)) \n" +
            ")) <= 10 \n" +
            "ORDER BY distance " , nativeQuery = true)
    List<Restoran> findByLocation(@Param("lat") double latitude, @Param("lon") double longitude);
}
