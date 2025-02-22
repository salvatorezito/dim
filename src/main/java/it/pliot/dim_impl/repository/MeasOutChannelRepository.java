package it.pliot.dim_impl.repository;

import it.pliot.dim_impl.data.SignalChannel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface MeasOutChannelRepository extends JpaRepository<SignalChannel, String> {
}
