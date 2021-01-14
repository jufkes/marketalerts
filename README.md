# marketalerts

API and service for pulling data from binance and doing work against it.

More to do.

## Usage

Make sure docker is installed.
```
docker run --name market-alerts -p 27017:27017 -d mongo:4.2.0
```

Open in your favorite IDE. Setup java 11. Make sure to install lombok. Run it.


To access data from the service, call the following endpoints: 

```
-- this first call will write to mongo --
http://localhost:8080/process?symbol=ethusdt&interval=4h&period=30
-- these do not and should be refactored to pull data from mongo
http://localhost:8080/smadata?symbol=ETHUSDT&interval=4h&period=10
http://localhost:8080/emadata?symbol=ETHUSDT&interval=4h&period=10
http://localhost:8080/macddata?symbol=ETHUSDT&interval=4h
```

| variable name | description | example |
| ----- | ------ | ------ |
| symbol | trade pair to return data on | ETHUSDT |
| interval | kline interval to use | 4h (options: 15m, 30m, 1h, 12h, 24h) |
| period | calculation value for moving averages | 10 |

## Webhook configuration

Update the discord.webhook param in the application.yaml with the discord webhook link.

## TODO

* sweep high / low alerts

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

## CICD

In order to deploy this to the cluster, the image tag needs to be incremented to a new version.

To accomplish that, update the tag on line 20 in this file:

./kustomize/deployment.yaml

Example: 
```
image: portr.ctl.io/private/marketalerts:1.0.1
```

Then use the same ID as the build tag in github Actions workflow here: .github/workflows/build.yml

Lines 22 and 25

```
- name: Build Docker image
  run: docker build -t portr.ctl.io/private/marketalerts:1.0.1 .

- name: Publish latest image
  run: docker push portr.ctl.io/private/marketalerts:1.0.1
```

Flux should handle the updating of the image in the cluster.