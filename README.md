# job4j_pooh

[![Build Status](https://app.travis-ci.com/RaduKostashchuk/job4j_pooh.svg?branch=master)](https://app.travis-ci.com/RaduKostashchuk/job4j_pooh)
[![codecov](https://codecov.io/gh/RaduKostashchuk/job4j_pooh/branch/master/graph/badge.svg?token=3PXGJBNC61)](https://codecov.io/gh/RaduKostashchuk/job4j_pooh)

## О проекте

Аналог асинхронной очереди.

Приложение запускается и слушает TCP порт.

Предусмотрено два типа клиентов: отправители и получатели.

У очереди есть два режима:

1. Общая очередь - создается одна очередь для всех получателей, после прочтения сообщение удаляется.

2. Персональная очередь - получатель подписывается на топик, сообщения адресованные этому топику

    сохраняются в очередях подписанных получателей.

## Сборка и конфигурация

Перед сборкой нужно указать TCP порт в файле PoohServer.java.

Сборка осуществляется командой: mvn package

## Контакты

Email: kostasc@mail.ru
Telegram: @rkostashchuk
