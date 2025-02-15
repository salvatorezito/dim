package it.pliot.dim_impl.repository;

import it.pliot.dim_impl.data.IotAdapterType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface IotAdapterRepository extends JpaRepository<IotAdapterType, String> {
}
