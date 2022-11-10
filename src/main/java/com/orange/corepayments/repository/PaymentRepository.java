package com.orange.corepayments.repository;

import com.orange.corepayments.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

//    @Transactional
//    @Modifying
//    @Query("UPDATE Payment p SET p.status_id =:statusId WHERE p.id=:id")
//    Payment updatePaymentStatus(@Param("status_id") Long statusId,
//                                @Param("id") Long id);

}
