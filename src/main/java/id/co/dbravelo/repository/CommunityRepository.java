package id.co.dbravelo.repository;

import id.co.dbravelo.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer>
{
    List<Community> findAllByOrderByCreatedAtDesc();
}
