package it.pliot.dim_impl.repository;

import it.pliot.dim_impl.data.IotAdapter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface IotAdapterRepository extends JpaRepository<IotAdapter, String> {
}
