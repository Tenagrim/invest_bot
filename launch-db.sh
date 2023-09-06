#!/bin/bash

#docker container run -p5432:5432 -v$(pwd)/../docker/volumes/invest_bot_db:/var/lib/postgresql/data  -e POSTGRES_DB=invest_bot -e POSTGRES_USER=invest_bot -e POSTGRES_PASSWORD=botbasepassword -d postgres
docker container run -p5432:5432 -e POSTGRES_DB=invest_bot -e POSTGRES_USER=invest_bot -e POSTGRES_PASSWORD=botbasepassword -d postgres