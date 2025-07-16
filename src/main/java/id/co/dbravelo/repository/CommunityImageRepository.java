package id.co.dbravelo.repository;

import id.co.dbravelo.model.CommunityImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityImageRepository extends JpaRepository<CommunityImage, Integer> {}
