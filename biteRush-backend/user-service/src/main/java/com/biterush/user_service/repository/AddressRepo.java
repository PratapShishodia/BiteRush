package com.biterush.user_service.repository;

import com.biterush.user_service.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface AddressRepo extends JpaRepository<Address, UUID> {
    Optional<Address> findByAddressId(UUID addressId);
    List<Address> findByUserUserId(UUID userId);
    @Modifying
    @Query("""
    UPDATE Address a
    SET a.isDefault = false
    WHERE a.user.userId = :userId
      AND a.addressId <> :addressId
""")
    void unsetDefaultAddress(
            @Param("userId") UUID userId,
            @Param("addressId") UUID addressId
    );
}
