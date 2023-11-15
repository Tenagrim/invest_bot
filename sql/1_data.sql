insert into app_authority(id, authority) values (1,'ROLE_VIEWER');
insert into app_authority(id, authority) values (2,'ROLE_EDITOR');

insert into app_user(id, locked, enabled, username, password) values (1, false, true, 'tenagrim', '$2y$10$e9FqVd5vAR9lO2nCLmW.DOYo7GG/tZPnDudUM94I5yev84TPqyWMq');
insert into app_user(id, locked, enabled, username, password) values (2, false, true, 'testuser', '$2a$12$eboOBLlAjW.CDXuu7pOP0e3etl1muzGaXsFbApYdPQux2J14IUdju');
insert into app_user(id, locked, enabled, username, password) values (3, false, true, 'investbotadmin', '$2a$12$Ew3DZ0lJ2pITE2EIAVzAVOhoM6fJ6jn7KXu88PxwRSzoK.IH6adiG');
insert into app_user_authority(user_id, authority_id) values (1,1);
insert into app_user_authority(user_id, authority_id) values (1,2);
insert into app_user_authority(user_id, authority_id) values (2,1);
insert into app_user_authority(user_id, authority_id) values (2,2);
insert into app_user_authority(user_id, authority_id) values (3,1);
insert into app_user_authority(user_id, authority_id) values (3,2);

INSERT INTO BOT_CONFIG_PROPERTIES(ID, SYSNAME, DESCRIPTION) values (1, 'chapter_sequence', 'Разделы в чате располагаются последовательно');


insert into chapter_type(id, sysname, name) values (1, 'BASE', 'Базовый');
insert into chapter_type(id, sysname, name) values (2, 'DEAL', 'Сделка');
insert into contact_type(id, sysname, name) values (1, 'MOBILE_PHONE', 'Мобильный телефон');
insert into paragraph_type(id, sysname, description) values (1, 'TEXT', 'text');
insert into paragraph_type(id, sysname, description) values (2, 'DOCUMENT', 'document');
insert into attachment_type(id, sysname, description) values (1, 'IMAGE', 'image');
insert into attachment_type(id, sysname, description) values (2, 'DOCUMENT', 'document');
insert into keyboard_type(id, sysname, description) values (1, 'INLINE', 'inline');
insert into keyboard_type(id, sysname, description) values (2, 'STATIC', 'static');
insert into integration_type(id, sysname, description) values (1, 'AMOCRM', 'amoCRM');
insert into INTEGRATION_CREDENTIAL_TYPE(id, sysname, description) values (1, 'JWT_TOKEN', 'access_token');
insert into INTEGRATION_CREDENTIAL_TYPE(id, sysname, description) values (2, 'REFRESH_TOKEN', 'refresh_token');
insert into INTEGRATION_CREDENTIAL_TYPE(id, sysname, description) values (3, 'API_URL', 'api url');
insert into INTEGRATION_CREDENTIAL_TYPE(id, sysname, description) values (4, 'CLIENT_ID', 'client id');
insert into INTEGRATION_CREDENTIAL_TYPE(id, sysname, description) values (5, 'CLIENT_SECRET', 'client secret');
insert into INTEGRATION_CREDENTIAL_TYPE(id, sysname, description) values (6, 'REDIRECT_URI', 'redirect uri');
insert into INTEGRATION_CREDENTIAL_TYPE(id, sysname, description) values (7, 'UTM_SOURCE_FIELD_ID', 'utm source field id');

insert into bot_config_version(id) values (1);
insert into data_version(id, bot_config_version_id, note ) values (1, 1,  'Версия 1');
insert into bot_config(id, bot_config_version_id, sysname, name, current_version_id) values (1, 1, 'INVEST_BOT', 'Город инвесторов', 1);
insert into bot_config_properties_values(bot_config_id,  property_id, value) values (1, 1, 'true');

insert into chapter_mark(key, bot_config_id, name) values (1, 1,'Гл. меню');
insert into chapter_mark(key, bot_config_id, name) values (2, 1,'Статический');
insert into chapter_mark(key, bot_config_id, name) values (3, 1,'Переменный');
insert into chapter_mark(key, bot_config_id, name) values (4, 1,'Отзыв');
insert into chapter_mark(key, bot_config_id, name) values (5, 1,'Сделка');
insert into chapter_mark(key, bot_config_id, name) values (6, 1,'Кейс');
insert into chapter_mark(key, bot_config_id, name) values (7, 1,'Венчурные');
insert into chapter_mark(key, bot_config_id, name) values (8, 1,'Внебиржевые');
insert into chapter_mark(key, bot_config_id, name) values (9, 1,'РФ');
insert into chapter_mark(key, bot_config_id, name) values (10, 1,'Зарубеж');
insert into chapter_mark(key, bot_config_id, name) values (11, 1,'Подробно');
insert into chapter_mark(key, bot_config_id, name) values (12, 1,'О нас');
insert into chapter_mark(key, bot_config_id, name) values (13, 1,'Спец.');

insert into node_position (id, x, y) VALUES (1,  100, 100);
insert into node_position (id, x, y) VALUES (2,  200, 100);
insert into node_position (id, x, y) VALUES (3,  300, 100);
insert into node_position (id, x, y) VALUES (4,  400, 100);
insert into node_position (id, x, y) VALUES (5,  500, 100);
insert into node_position (id, x, y) VALUES (6,  600, 100);
insert into node_position (id, x, y) VALUES (7,  700, 100);
insert into node_position (id, x, y) VALUES (8,  800, 100);
insert into node_position (id, x, y) VALUES (9,  900, 100);
insert into node_position (id, x, y) VALUES (10, 1000, 100);
insert into node_position (id, x, y) VALUES (11, 1100, 100);
insert into node_position (id, x, y) VALUES (12, 1200, 100);
insert into node_position (id, x, y) VALUES (13, 1300, 100);
insert into node_position (id, x, y) VALUES (14, 1400, 100);
insert into node_position (id, x, y) VALUES (15, 1500, 100);
insert into node_position (id, x, y) VALUES (16, 1600, 100);
insert into node_position (id, x, y) VALUES (17, 1700, 100);
insert into node_position (id, x, y) VALUES (18, 1800, 100);
insert into node_position (id, x, y) VALUES (19, 1900, 100);
insert into node_position (id, x, y) VALUES (20, 2000, 100);
-- insert into node_position ( x, y) VALUES ( 2000, 100);
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (1, 1, 1, 1, 1, 'Старт', E'Город инвесторов— это инвестиционное агентство. Мы предлагаем вашему вниманию уникальные сделки с высокой доходностью в направлениях: инвестиции в недвижимость, венчурное и внебиржевое инвестирование.\n\nЧтобы получить более подробную информацию, выберите интересующий раздел');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (2, 2, 1, 2, 1, 'Актуальные сделки', 'Мы работаем сразу по нескольким направлениям. Сделка под ключ -- инвестиции в недвижимость Внебиржевое Венчурное Выберите направление, которое вам более интересно, чтобы мы предложили вам актуальные сделки');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (3, 3, 1, 3, 1, 'О Нас',  E'Город инвесторов — это команда профессионалов с более чем 10-ти летним опытом в инвестициях в доходную недвижимость(квартиры-студии, квартиры, земельные участки, строительство мини-гостиниц и СПА\банных комплексов, коммерческая недвижимость, частные дома).\n\nОрганизаторами инвест-пула совместно с инвесторами было выкуплено и реализовано за всё время более 200 сделок.\n\nКоличество собственных инвестиционных сделок с недвижимостью участников сообщества с 2018 года составляет больше 550 инвестиционных квартир общей стоимостью объектов более 4 000 000 000 рублей. Фактическая доходность по сделкам составляет 50-90% годовых на вложенный капитал[.](https://img1.teletype.in/files/ce/21/ce2132ed-b168-4d52-86b0-04a3cf434a60.png)');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (4, 4, 1, 4, 1, 'Запрос контакта','Оставьте ваш номр телефона и менеджер свяжется с вами в течение 15-ти минут!');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (5, 5, 1, 5, 1, 'Как инвестировать с нами','Информация + инфографика');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (6, 6, 1, 6, 1, 'Информация','Информация');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (7, 7, 1, 7, 1, 'Сотрудничество с нами', 'Сотрудничество с нами');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (8, 8, 1, 8, 1, 'Условия','Условия');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (9, 9, 1, 9, 1, 'Номера','Список доверенных номеров (добавить по отделам)');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (10,10,1, 10, 1,'Недвижимость в РФ', 'Мы лидеры на рынке недвижимоости, у нас 1000+ уникальных сделок с российской недвижимостью под 70+ % годовых. Ознакомьтесь  с нашими актуальными сделками ниже, выбрав интересующий объект');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (11,11,1, 11, 1, '_', 'Что то типа добро пожаловать');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (12,12,1, 12, 2, 'Сделка 2',E'💥🌇АПАРТАМЕНТЫ на Бали со скидкой 10% на предстарте продаж!\n\n🔽Статус: Активно\n\n⚠️ Сделка доступна только до 18.09.2023\n\n1️⃣ Параметры недвижимости:\n\n▪️специальная скидка только для клиентов агентства: 10%\n▪️Лучшее предложение на рынке по критерию цена/качество\n▪️апартаменты в 6 минутах от океана\n▪️качественная чистовая отделка и мебель\n▪️отсутствие прямых конкурентов в локации\n▪️вся инфраструктура для жизни внутри комплекса\n▪️живописный район с высокой ликвидностью');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (13,13,1, 13, 2, 'Сделка 2 подробности',E'💥🌇АПАРТАМЕНТЫ на Бали со скидкой 10% на предстарте продаж!\n\n🔽Статус: Активно\n\n⚠️ Сделка доступна только до 18.09.2023\n\n1️⃣ Параметры недвижимости:\n\n▪️специальная скидка только для клиентов агентства: 10%\n▪️Лучшее предложение на рынке по критерию цена/качество\n▪️апартаменты в 6 минутах от океана\n▪️качественная чистовая отделка и мебель\n▪️отсутствие прямых конкурентов в локации\n▪️вся инфраструктура для жизни внутри комплекса\n▪️живописный район с высокой ликвидностью\n\n2️⃣ Параметры инвестиционной сделки:\n\nЦена недвижимости: от 7 млн. рублей\n\nСумма для входа в сделку: от 1,8 млн. рублей\n\nВложения после покупки: платежи по рассрочке\n\nСрок инвестиций: 12 месяцев по основной стратегии\n\n🔥 Доходность: 52% - 64% годовых или от 20 350$ до 25 095$\n\n🔗Изучите расчёт доходности по этой ссылке (https://docs.google.com/spreadsheets/d/1twVh9P1CbaJ6-XiEX6Jupa9Eqav3vjAq/edit#gid=1756360308)\n\nМы отобрали в пул 8 лучших апартаментов!\n\n3️⃣ Почему стоит инвестировать в этот объект:\n\n▪️Вы покупаете апартаменты на закрытых продажах со специальной скидкой 10% только для клиентов агентства.\n▪️Индивидуальный график рассрочки, пониженный первоначальный взнос\n▪️Чистовая отделка\n▪️Недвижимость можно сдавать в аренду. Высокая заполняемость объектов при сдаче в аренду — в туристических локациях — 80-90%. Сезон длится 365 дней в году! Годовой доход от аренды от 27%\n▪️Стоимость апартаментов значительно ниже рынка. Конкурентов в локации практически нет.\n▪️Индонезия входит ТОП 5 в прогнозируемом рейтинге экономик мира к 2030 году (по прогнозам Всемирного банка и Международного валютного фонда)\n\n4️⃣ Гарантии и безопасность инвестора в сделке:\n\n▪️Вы покупаете недвижимость на себя. Вы — собственник недвижимости\n▪️У застройщика есть все необходимые документы и разрешения на строительство\n▪️Сделка проверена инвестиционным комитетом Агентства\n▪️На всех этапах сделки вас сопровождает персональный менеджер\n\n5️⃣ Что нужно сделать, чтобы стать собственником недвижимости:\n\n🔗оставить заявку по ссылке (https://gc.gi-agency.ru/agency?&utm_source=bali_2&utm_medium=pool&utm_campaign=agency) до 19.09.2023\n▪️подписать агентсткий договор и ознакомиться с подробными условиями проекта\n▪️принять решение о бронировании и выбрать лот\n▪️оформить недвижимость удалённо с помощью персонального инвестиционного менеджера\n\nС большой вероятностью, лоты с минимальной стоимостью закончатся в первую неделю (их всего 8)\nЕсли вы рассматриваете инвестиции в зарубежную недвижимость, вам точно следует изучить эту сделку подробно.\n\n🔗Успейте зафиксировать самый выгодный лот —  (https://gc.gi-agency.ru/agency?utm_source=moscow_11&utm_medium=offclub&utm_campaign=offclub)оставляйте заявку по ссылке, чтобы выбрать для себя лучший инвестиционный вариант! (https://gc.gi-agency.ru/agency?&utm_source=bali_2&utm_medium=pool&utm_campaign=agency)\n\nС уважением, команда Агентства «Город Инвесторов»');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (14,14,1, 14, 1, 'Как с нами сотрудничать',E'1. Оставьте заявку, менеджер свяжется с вами для уточнения деталей\n2. Подпишите агентский договор\n3. Выбор лота и бронь апартаментов\n4. Заключить Договор долевого участия (контракт) с Застройщиком, оплата комиссионных');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (15,15,1, 15, 1, 'Библиотека инвестора','(идея) продумать -- для сайта + для бота. Полезные материалы');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (16,16,1, 16, 1, 'Контакты', E'Список доверенных номеров\n(добавить по отделам)');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (17,17,1, 17, 1, 'Соцсети',E'Список доверенных соцсетей\n(добавить по отделам)');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (18,18,1, 18, 2, 'Сделка 1', E'🏠 Доходные дома в Ленинградской области с отделкой под ключ и полным оснащением\n\nЦена недвижимости: 13 300 000 рублей\n\nСумма для входа в сделку: 1 764 000 рублей\n\nВложения после покупки: ипотека с платежом от 90 тыс. рублей в месяц\n\n❗️Сдача в аренду через 9 месяцев перекроет ипотечный платёж на 100%\n\nСрок инвестиций: 24-36 месяцев по основной стратегии\n\n🔥 Доходность: 51% - 68% годовых или от 2,4 млн до 3,2 млн рублей в зависимости от срока\n\n\n⚠️ Сделка доступна только до 15.07.2023. 15 июля застройщик поднимет цены на 500 тыс.рублей');
insert into chapter (id, item_id, data_version_id, node_position_id, chapter_type_id, note, text) values (19,19,1, 19, 2, 'Сделка 1 подробности', E'🏠 Доходные дома в Ленинградской области с отделкой под ключ и полным оснащением\n\n🟢Статус: Активно\n\n1️⃣ Параметры недвижимости:\n\n▪️Вы покупаете дом с сниженным первоначальным взносом: 1,3 млн рублей\n▪️Местоположение на Карельском перешейке на берегу озера\n▪️100% домов находятся в сосновом лесу\n▪️Качественная чистовая отделка и полное оснащение дома, вплоть до игровой приставки\n▪️Отсутствие прямых конкурентов в локации.\n▪️Вся инфраструктура для отдыха и жизни рядом\n▪️Хорошая транспортная доступность до СПб и всех лид-магнитов в локации\n▪️Один из самых элитных районов Ленинградской области как для жизни, так и для сдачи в аренду\n\n2️⃣ Параметры инвестиционной сделки:\n\nЦена недвижимости: 13 300 000 рублей\n\nСумма для входа в сделку: 1 764 000 рублей\n\nВложения после покупки: ипотека с платежом от 90 тыс. рублей в месяц\n\n❗️Сдача в аренду через 9 месяцев перекроет ипотечный платёж на 100%\n\nСрок инвестиций: 24-36 месяцев по основной стратегии\n\n🔥 Доходность: 51% - 68% годовых или от 2,4 млн до 3,2 млн рублей в зависимости от срока\n\nНаша команда отобрала в пул 8 лучших участков, на которых будут построены дома.\n\n⚠️ Сделка доступна только до 15.07.2023. 15 июля застройщик поднимет цены на 500 тыс.рублей\n\n🔗Изучите расчёт доходности по этой ссылке (https://docs.google.com/spreadsheets/d/16H87tgdJ3v4HvNv-TtHl-oi0VXzfPhCzKoYAIIqgoEA/edit#gid=1823302455)\n\n3️⃣ Почему мы рекомендуем данную недвижимость к покупке:\n\n▪️Инвестор приобретает недвижимость в ликвидной локации с огромным количеством внешних и внутренних точек притяжения\n▪️Дома с шикарным расположением среди Карельского леса и на берегу озера\n▪️Чистовая отделка под ключ даёт инвестору План Б: недвижимость можно и нужно сдавать в аренду, перекрывая ипотечный платёж\n▪️Отсутствие конкурентов на продажу в локации\n▪️Первая очередь застройщика (50 коттеджей) распродалась за 5 месяцев\n▪️В 2023 году для дальнейшего развития инфраструктуры и реализации программ ЛО будет выделено 185,6 млн рублей.\n\n4️⃣ Гарантии и безопасность инвестора в сделке:\n\n▪️Вы покупаете недвижимость на себя. Вы - собственник недвижимости\n▪️Объект аккредитован в банках Сбербанк, Росбанк, ДомРФ\n▪️Сделка проверена инвестиционным комитетом Агентства\n▪️Платежи по ипотеке перекрывают доход от аренды с 9 месяца после входа в сделку\n▪️На всех этапах сделки вас сопровождает персональный менеджер.\n\n5️⃣ Что нужно сделать, чтобы стать собственником недвижимости:\n\n🔗оставить заявку по ссылке (https://gc.gi-agency.ru/agency?&utm_source=Spb_7&utm_medium=pool&utm_campaign=agency) до 30.06.2023\n▪️подписать агентсткий договор и ознакомиться с подробными условиями проекта\n▪️принять решение о бронировании и выбрать лот\n▪️оформить недвижимость удалённо с помощью персонального инвестиционного менеджера\n\nЕсли вы рассматриваете инвестиции в российскую недвижимость и вам интересен пассивный доход на автомате, обязательно изучите эту сделку подробно.\n\nВас ждут:\n▪️персональная консультация со специалистом агентства (при необходимости — в zoom)\n▪️сайт с подробным описанием проекта\n▪️аналитика по конкурентам\n▪️консультация с ипотечным брокером\n▪️чек-лист по входу в сделку и полное сопровождение\n\n🔗Оставляйте заявку по ссылке, чтобы выбрать для себя лучший инвестиционный лот (https://gc.gi-agency.ru/agency?&utm_source=Spb_7&utm_medium=pool&utm_campaign=agency)\n\nС уважением, команда Агентства "Город Инвесторов"');


insert into PARAGRAPH (id, chapter_id, text) values (1, 1,  E'Город инвесторов— это инвестиционное агентство. Мы предлагаем вашему вниманию уникальные сделки с высокой доходностью в направлениях: инвестиции в недвижимость, венчурное и внебиржевое инвестирование.\n\nЧтобы получить более подробную информацию, выберите интересующий раздел');
insert into PARAGRAPH (id, chapter_id, text) values (2, 2,  'Мы работаем сразу по нескольким направлениям. Сделка под ключ -- инвестиции в недвижимость Внебиржевое Венчурное Выберите направление, которое вам более интересно, чтобы мы предложили вам актуальные сделки');
insert into PARAGRAPH (id, chapter_id, text) values (3, 3,  E'Город инвесторов — это команда профессионалов с более чем 10-ти летним опытом в инвестициях в доходную недвижимость(квартиры-студии, квартиры, земельные участки, строительство мини-гостиниц и СПА\банных комплексов, коммерческая недвижимость, частные дома).\n\nОрганизаторами инвест-пула совместно с инвесторами было выкуплено и реализовано за всё время более 200 сделок.\n\nКоличество собственных инвестиционных сделок с недвижимостью участников сообщества с 2018 года составляет больше 550 инвестиционных квартир общей стоимостью объектов более 4 000 000 000 рублей. Фактическая доходность по сделкам составляет 50-90% годовых на вложенный капитал[.](https://img1.teletype.in/files/ce/21/ce2132ed-b168-4d52-86b0-04a3cf434a60.png)');
insert into PARAGRAPH (id, chapter_id, text) values (4, 4,  'Оставьте ваш номр телефона и менеджер свяжется с вами в течение 15-ти минут!');
insert into PARAGRAPH (id, chapter_id, text) values (5, 5,  'Информация + инфографика');
insert into PARAGRAPH (id, chapter_id, text) values (6, 6,  'Информация');
insert into PARAGRAPH (id, chapter_id, text) values (7, 7,  'Сотрудничество с нами');
insert into PARAGRAPH (id, chapter_id, text) values (8, 8,  'Условия');
insert into PARAGRAPH (id, chapter_id, text) values (9, 9,  'Список доверенных номеров (добавить по отделам)');
insert into PARAGRAPH (id, chapter_id, text) values (10,10, 'Мы лидеры на рынке недвижимоости, у нас 1000+ уникальных сделок с российской недвижимостью под 70+ % годовых. Ознакомьтесь  с нашими актуальными сделками ниже, выбрав интересующий объект');
insert into PARAGRAPH (id, chapter_id, text) values (11,11, 'Что то типа добро пожаловать');
insert into PARAGRAPH (id, chapter_id, text) values (12,12, E'💥🌇АПАРТАМЕНТЫ на Бали со скидкой 10% на предстарте продаж!\n\n🔽Статус: Активно\n\n⚠️ Сделка доступна только до 18.09.2023\n\n1️⃣ Параметры недвижимости:\n\n▪️специальная скидка только для клиентов агентства: 10%\n▪️Лучшее предложение на рынке по критерию цена/качество\n▪️апартаменты в 6 минутах от океана\n▪️качественная чистовая отделка и мебель\n▪️отсутствие прямых конкурентов в локации\n▪️вся инфраструктура для жизни внутри комплекса\n▪️живописный район с высокой ликвидностью');
insert into PARAGRAPH (id, chapter_id, text) values (13,13, E'💥🌇АПАРТАМЕНТЫ на Бали со скидкой 10% на предстарте продаж!\n\n🔽Статус: Активно\n\n⚠️ Сделка доступна только до 18.09.2023\n\n1️⃣ Параметры недвижимости:\n\n▪️специальная скидка только для клиентов агентства: 10%\n▪️Лучшее предложение на рынке по критерию цена/качество\n▪️апартаменты в 6 минутах от океана\n▪️качественная чистовая отделка и мебель\n▪️отсутствие прямых конкурентов в локации\n▪️вся инфраструктура для жизни внутри комплекса\n▪️живописный район с высокой ликвидностью\n\n2️⃣ Параметры инвестиционной сделки:\n\nЦена недвижимости: от 7 млн. рублей\n\nСумма для входа в сделку: от 1,8 млн. рублей\n\nВложения после покупки: платежи по рассрочке\n\nСрок инвестиций: 12 месяцев по основной стратегии\n\n🔥 Доходность: 52% - 64% годовых или от 20 350$ до 25 095$\n\n🔗Изучите расчёт доходности по этой ссылке (https://docs.google.com/spreadsheets/d/1twVh9P1CbaJ6-XiEX6Jupa9Eqav3vjAq/edit#gid=1756360308)\n\nМы отобрали в пул 8 лучших апартаментов!\n\n3️⃣ Почему стоит инвестировать в этот объект:\n\n▪️Вы покупаете апартаменты на закрытых продажах со специальной скидкой 10% только для клиентов агентства.\n▪️Индивидуальный график рассрочки, пониженный первоначальный взнос\n▪️Чистовая отделка\n▪️Недвижимость можно сдавать в аренду. Высокая заполняемость объектов при сдаче в аренду — в туристических локациях — 80-90%. Сезон длится 365 дней в году! Годовой доход от аренды от 27%\n▪️Стоимость апартаментов значительно ниже рынка. Конкурентов в локации практически нет.\n▪️Индонезия входит ТОП 5 в прогнозируемом рейтинге экономик мира к 2030 году (по прогнозам Всемирного банка и Международного валютного фонда)\n\n4️⃣ Гарантии и безопасность инвестора в сделке:\n\n▪️Вы покупаете недвижимость на себя. Вы — собственник недвижимости\n▪️У застройщика есть все необходимые документы и разрешения на строительство\n▪️Сделка проверена инвестиционным комитетом Агентства\n▪️На всех этапах сделки вас сопровождает персональный менеджер\n\n5️⃣ Что нужно сделать, чтобы стать собственником недвижимости:\n\n🔗оставить заявку по ссылке (https://gc.gi-agency.ru/agency?&utm_source=bali_2&utm_medium=pool&utm_campaign=agency) до 19.09.2023\n▪️подписать агентсткий договор и ознакомиться с подробными условиями проекта\n▪️принять решение о бронировании и выбрать лот\n▪️оформить недвижимость удалённо с помощью персонального инвестиционного менеджера\n\nС большой вероятностью, лоты с минимальной стоимостью закончатся в первую неделю (их всего 8)\nЕсли вы рассматриваете инвестиции в зарубежную недвижимость, вам точно следует изучить эту сделку подробно.\n\n🔗Успейте зафиксировать самый выгодный лот —  (https://gc.gi-agency.ru/agency?utm_source=moscow_11&utm_medium=offclub&utm_campaign=offclub)оставляйте заявку по ссылке, чтобы выбрать для себя лучший инвестиционный вариант! (https://gc.gi-agency.ru/agency?&utm_source=bali_2&utm_medium=pool&utm_campaign=agency)\n\nС уважением, команда Агентства «Город Инвесторов»');
insert into PARAGRAPH (id, chapter_id, text) values (14,14, E'1. Оставьте заявку, менеджер свяжется с вами для уточнения деталей\n2. Подпишите агентский договор\n3. Выбор лота и бронь апартаментов\n4. Заключить Договор долевого участия (контракт) с Застройщиком, оплата комиссионных');
insert into PARAGRAPH (id, chapter_id, text) values (15,15, '(идея) продумать -- для сайта + для бота. Полезные материалы');
insert into PARAGRAPH (id, chapter_id, text) values (16,16, E'Список доверенных номеров\n(добавить по отделам)');
insert into PARAGRAPH (id, chapter_id, text) values (17,17, E'Список доверенных соцсетей\n(добавить по отделам)');
insert into PARAGRAPH (id, chapter_id, text) values (18,18, E'🏠 Доходные дома в Ленинградской области с отделкой под ключ и полным оснащением\n\nЦена недвижимости: 13 300 000 рублей\n\nСумма для входа в сделку: 1 764 000 рублей\n\nВложения после покупки: ипотека с платежом от 90 тыс. рублей в месяц\n\n❗️Сдача в аренду через 9 месяцев перекроет ипотечный платёж на 100%\n\nСрок инвестиций: 24-36 месяцев по основной стратегии\n\n🔥 Доходность: 51% - 68% годовых или от 2,4 млн до 3,2 млн рублей в зависимости от срока\n\n\n⚠️ Сделка доступна только до 15.07.2023. 15 июля застройщик поднимет цены на 500 тыс.рублей');
insert into PARAGRAPH (id, chapter_id, text) values (19,19, E'🏠 Доходные дома в Ленинградской области с отделкой под ключ и полным оснащением\n\n🟢Статус: Активно\n\n1️⃣ Параметры недвижимости:\n\n▪️Вы покупаете дом с сниженным первоначальным взносом: 1,3 млн рублей\n▪️Местоположение на Карельском перешейке на берегу озера\n▪️100% домов находятся в сосновом лесу\n▪️Качественная чистовая отделка и полное оснащение дома, вплоть до игровой приставки\n▪️Отсутствие прямых конкурентов в локации.\n▪️Вся инфраструктура для отдыха и жизни рядом\n▪️Хорошая транспортная доступность до СПб и всех лид-магнитов в локации\n▪️Один из самых элитных районов Ленинградской области как для жизни, так и для сдачи в аренду\n\n2️⃣ Параметры инвестиционной сделки:\n\nЦена недвижимости: 13 300 000 рублей\n\nСумма для входа в сделку: 1 764 000 рублей\n\nВложения после покупки: ипотека с платежом от 90 тыс. рублей в месяц\n\n❗️Сдача в аренду через 9 месяцев перекроет ипотечный платёж на 100%\n\nСрок инвестиций: 24-36 месяцев по основной стратегии\n\n🔥 Доходность: 51% - 68% годовых или от 2,4 млн до 3,2 млн рублей в зависимости от срока\n\nНаша команда отобрала в пул 8 лучших участков, на которых будут построены дома.\n\n⚠️ Сделка доступна только до 15.07.2023. 15 июля застройщик поднимет цены на 500 тыс.рублей\n\n🔗Изучите расчёт доходности по этой ссылке (https://docs.google.com/spreadsheets/d/16H87tgdJ3v4HvNv-TtHl-oi0VXzfPhCzKoYAIIqgoEA/edit#gid=1823302455)\n\n3️⃣ Почему мы рекомендуем данную недвижимость к покупке:\n\n▪️Инвестор приобретает недвижимость в ликвидной локации с огромным количеством внешних и внутренних точек притяжения\n▪️Дома с шикарным расположением среди Карельского леса и на берегу озера\n▪️Чистовая отделка под ключ даёт инвестору План Б: недвижимость можно и нужно сдавать в аренду, перекрывая ипотечный платёж\n▪️Отсутствие конкурентов на продажу в локации\n▪️Первая очередь застройщика (50 коттеджей) распродалась за 5 месяцев\n▪️В 2023 году для дальнейшего развития инфраструктуры и реализации программ ЛО будет выделено 185,6 млн рублей.\n\n4️⃣ Гарантии и безопасность инвестора в сделке:\n\n▪️Вы покупаете недвижимость на себя. Вы - собственник недвижимости\n▪️Объект аккредитован в банках Сбербанк, Росбанк, ДомРФ\n▪️Сделка проверена инвестиционным комитетом Агентства\n▪️Платежи по ипотеке перекрывают доход от аренды с 9 месяца после входа в сделку\n▪️На всех этапах сделки вас сопровождает персональный менеджер.\n\n5️⃣ Что нужно сделать, чтобы стать собственником недвижимости:\n\n🔗оставить заявку по ссылке (https://gc.gi-agency.ru/agency?&utm_source=Spb_7&utm_medium=pool&utm_campaign=agency) до 30.06.2023\n▪️подписать агентсткий договор и ознакомиться с подробными условиями проекта\n▪️принять решение о бронировании и выбрать лот\n▪️оформить недвижимость удалённо с помощью персонального инвестиционного менеджера\n\nЕсли вы рассматриваете инвестиции в российскую недвижимость и вам интересен пассивный доход на автомате, обязательно изучите эту сделку подробно.\n\nВас ждут:\n▪️персональная консультация со специалистом агентства (при необходимости — в zoom)\n▪️сайт с подробным описанием проекта\n▪️аналитика по конкурентам\n▪️консультация с ипотечным брокером\n▪️чек-лист по входу в сделку и полное сопровождение\n\n🔗Оставляйте заявку по ссылке, чтобы выбрать для себя лучший инвестиционный лот (https://gc.gi-agency.ru/agency?&utm_source=Spb_7&utm_medium=pool&utm_campaign=agency)\n\nС уважением, команда Агентства "Город Инвесторов"');


insert into command (id, text, chapter_id) values (1, '/start', 1);


insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (1, 1, 2, 'Актуальные сделки для инвестирования');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (1, 2, 3, 'О нас');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (1, 2, 5, 'Как начать инвестировать с нами');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (1, 3, 7, 'Сотрудничество с нами');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (1, 3, 4, 'Связаться с нами');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (1, 4, 16, 'Наши контакты');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (1, 4, 17, 'Наши соцсети');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (1, 5, 15, 'Библиотека инвестора');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (2, 1, 10, 'Недвижимость в РФ');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (2, 2, 2, 'Недвижимость за рубежом');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (2, 3, 2, 'Внебиржевое');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (2, 4, 2, 'Венчурное');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (2, 5, 14, 'Как начать инвестировать с нами');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (2, 6, 1, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (3, 1, 1, 'Частые вопросы');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (3, 1, 1, 'Отзывы');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (3, 2, 1, 'Наши кейсы');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (3, 2, 4, 'Подобрать сделку!');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (3, 3, 1, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (4, 1, 1, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (5, 1, 1, 'Что такое сделка под ключ');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (5, 2, 4, 'Подобрать сделку!');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (5, 3, 1, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (6, 1, 5, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (7, 1, 1, 'Хочу приносить сделки');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (7, 1, 1, 'Хочу приводить клиентов');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (7, 2, 1, 'Назад');

insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (8, 1,1, 'Подробнее об условиях сотрудничества');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (8, 2,1, 'Начать сотрудничество');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (8, 3, 7, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (9, 1, 1, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (10, 1, 18, 'Апартаменты СПБ');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (10, 2, 12, 'Апарт-отель доходность 20% годовых');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (10, 3, 12, 'Новостройка в Сочи');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (10, 4, 2, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (12, 1, 13, 'Подробнее');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (12, 2, 4, 'Заказать звонок менеджера');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (12, 3, 10, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (13, 1, 4, 'Заказать звонок менеджера');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (13, 2, 12, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (14, 1, 4, 'Начать инвестировать!');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (14, 2, 2, 'Назад');

insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (15, 1, 1, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (16, 1, 1, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (17, 1, 1, 'Назад');

insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (18, 1, 19, 'Подробнее');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (18, 2, 4, 'Заказать звонок менеджера');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (18, 3, 10, 'Назад');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (19, 1, 4, 'Заказать звонок менеджера');
insert into CHAPTER_BUTTON (chapter_id, placement, target_chapter_id, text) values (19, 2, 18, 'Назад');


insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (1, 1, 2, 'Актуальные сделки для инвестирования');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (1, 2, 3, 'О нас');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (1, 2, 5, 'Как начать инвестировать с нами');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (1, 3, 7, 'Сотрудничество с нами');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (1, 3, 4, 'Связаться с нами');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (1, 4, 16, 'Наши контакты');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (1, 4, 17, 'Наши соцсети');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (1, 5, 15, 'Библиотека инвестора');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (2, 1, 10, 'Недвижимость в РФ');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (2, 2, 2, 'Недвижимость за рубежом');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (2, 3, 2, 'Внебиржевое');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (2, 4, 2, 'Венчурное');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (2, 5, 14, 'Как начать инвестировать с нами');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (2, 6, 1, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (3, 1, 1, 'Частые вопросы');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (3, 1, 1, 'Отзывы');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (3, 2, 1, 'Наши кейсы');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (3, 2, 4, 'Подобрать сделку!');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (3, 3, 1, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (4, 1, 1, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (5, 1, 1, 'Что такое сделка под ключ');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (5, 2, 4, 'Подобрать сделку!');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (5, 3, 1, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (6, 1, 5, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (7, 1, 1, 'Хочу приносить сделки');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (7, 1, 1, 'Хочу приводить клиентов');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (7, 2, 1, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (8, 1,1, 'Подробнее об условиях сотрудничества');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (8, 2,1, 'Начать сотрудничество');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (8, 3, 7, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (9, 1, 1, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (10, 1, 18, 'Апартаменты СПБ');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (10, 2, 12, 'Апарт-отель доходность 20% годовых');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (10, 3, 12, 'Новостройка в Сочи');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (10, 4, 2, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (12, 1, 13, 'Подробнее');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (12, 2, 4, 'Заказать звонок менеджера');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (12, 3, 10, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (13, 1, 4, 'Заказать звонок менеджера');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (13, 2, 12, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (14, 1, 4, 'Начать инвестировать!');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (14, 2, 2, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (15, 1, 1, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (16, 1, 1, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (17, 1, 1, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (18, 1, 19, 'Подробнее');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (18, 2, 4, 'Заказать звонок менеджера');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (18, 3, 10, 'Назад');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (19, 1, 4, 'Заказать звонок менеджера');
insert into paragraph_button (paragraph_id, placement, target_chapter_id, text) values (19, 2, 18, 'Назад');


-- insert into CHAPTER_BUTTON (chapter_id, target_chapter_id, text) values (1,1, '');
--
-- insert into CHAPTER_BUTTON (chapter_id, target_chapter_id, text) values (1,1, '');










