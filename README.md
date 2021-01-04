# marketalerts

API and service for pulling data from binance and doing work against it.

More to do.

## Usage

Open in your favorite IDE. Setup java 11. Run it. 

To access data from the service, call the following: 

```http://localhost:8080/candlesticks?symbol=<symbol>```

Where `symbol` is the coin pair you want data from. **Note** it must be all caps because i didnt account for that yet. 

Example: 

```
http://localhost:8080/candlesticks?symbol=ETHUSDT
```

## TODO

* docker and kubernetes setup
* jenkins build (or github actions) 
* flux setup (github build trigger)
* sweep high / low alerts
* discord integration
* 10/20 moving average tracker
* macd tracker
* rsi tracker
* save all the things to a mongo record for dashboard integration
