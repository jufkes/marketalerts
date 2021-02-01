package io.crypto.marketalerts.model.kline;

import io.crypto.marketalerts.model.EmaData;
import io.crypto.marketalerts.model.MacdData;
import io.crypto.marketalerts.model.RsiData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KlineRecord {

    @Id
    private String id;
    private List<KlineMessage.KlineData> klines;

}
