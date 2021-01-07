# marketalerts

API and service for pulling data from binance and doing work against it.

More to do.

## Local Development Requirements
Install docker

## Usage

### Local Development Database
Make sure docker is installed.
```
docker run --name market-alerts -p 27017:27017 -d mongo:4.2.0
```

Open in your favorite IDE. Setup java 11. Make sure to install lombok. Run it.

 

To access data from the service, call the following: 

```http://localhost:8080/smadata?symbol=<symbol>&interval=4h&period=10```

Where `symbol` is the coin pair you want data from. **Note** it must be all caps because i didnt account for that yet. 

Example: 

```
http://localhost:8080/smadata?symbol=ETHUSDT&interval=4h&period=10
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

## Binance websocket info:

wss://stream.binance.com:9443/ws/<streamName>

send a subscriber payload of:

```{
"method": "SUBSCRIBE",
"params":
[
"ethusdt@kline_5m"
],
"id": 1
}```
