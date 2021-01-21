package io.crypto.marketalerts.repository;

import io.crypto.marketalerts.model.Alert;
import io.crypto.marketalerts.model.Interval;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlertRepository extends MongoRepository<Alert, Interval> {

}
