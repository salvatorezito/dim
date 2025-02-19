package it.pliot.dim_impl.repository;

import it.pliot.dim_impl.data.GatewayConf;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface  GatewayConfRepository  extends JpaRepository<GatewayConf, String> {
}
