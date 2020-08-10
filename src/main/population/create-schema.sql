
    create table `accounting_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `status` integer,
        `title` varchar(255),
        `bookkeeper_id` integer not null,
        `round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `activity` (
       `id` integer not null,
        `version` integer not null,
        `amount_amount` double precision,
        `amount_currency` varchar(255),
        `end_date` datetime(6),
        `start_date` datetime(6),
        `title` varchar(255),
        `round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `activity_sector` (
       `id` integer not null,
        `version` integer not null,
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `url` varchar(255),
        `card_id` integer,
        `patron_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm_name` varchar(255),
        `responsibility` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `casasola_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `favourite_color` varchar(255),
        `favourite_number` integer,
        `name` varchar(255),
        `surname` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `average_goal` varchar(255),
        `average_reward_amount` double precision,
        `average_reward_currency` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `expert_goal` varchar(255),
        `expert_reward_amount` double precision,
        `expert_reward_currency` varchar(255),
        `rookie_goal` varchar(255),
        `rookie_reward_amount` double precision,
        `rookie_reward_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `coleto_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `age` integer,
        `degree` varchar(255),
        `favourite_subject` varchar(255),
        `name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `credit_card` (
       `id` integer not null,
        `version` integer not null,
        `brand` varchar(255),
        `cvv` varchar(255),
        `expiration_date` varchar(255),
        `holder` varchar(255),
        `number` varchar(255),
        `patron_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `discussion_forum` (
       `id` integer not null,
        `version` integer not null,
        `investment_round_id` integer not null,
        `investor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `discussion_forum_message` (
       `discussion_forum_id` integer not null,
        `messages_id` integer not null
    ) engine=InnoDB;

    create table `entrepreneur` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` tinyblob,
        `qualification_record` varchar(255),
        `skills_record` varchar(255),
        `start_up_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `inquire` (
       `id` integer not null,
        `version` integer not null,
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `email` varchar(255),
        `max_money_amount` double precision,
        `max_money_currency` varchar(255),
        `min_money_amount` double precision,
        `min_money_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_application` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `justification` varchar(255),
        `money_offer_amount` double precision,
        `money_offer_currency` varchar(255),
        `statement` varchar(255),
        `status` integer,
        `ticker` varchar(255),
        `investment_applied_id` integer not null,
        `investor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round` (
       `id` integer not null,
        `version` integer not null,
        `amount_amount` double precision,
        `amount_currency` varchar(255),
        `creation_date` datetime(6),
        `description` varchar(255),
        `kind_of_round` integer,
        `link` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        `entrepreneur_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round_investment_application` (
       `investment_round_id` integer not null,
        `application_id` integer not null
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm_name` varchar(255),
        `profile` varchar(255),
        `sector` tinyblob,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `title` varchar(255),
        `forum_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message_tags` (
       `message_id` integer not null,
        `tags` varchar(255)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `header_picture` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice_related_notices` (
       `notice_id` integer not null,
        `related_notices` varchar(255)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `email` varchar(255),
        `max_money_amount` double precision,
        `max_money_currency` varchar(255),
        `min_money_amount` double precision,
        `min_money_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `patron` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `organisation_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `spam_list` (
       `id` integer not null,
        `version` integer not null,
        `spam_threshold` double precision,
        primary key (`id`)
    ) engine=InnoDB;

    create table `spam_word` (
       `id` integer not null,
        `version` integer not null,
        `english_translation` varchar(255),
        `spanish_translation` varchar(255),
        `spam_list_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology_record` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `email` varchar(255),
        `inventor` varchar(255),
        `open_source` bit,
        `stars` integer,
        `title` varchar(255),
        `website` varchar(255),
        `activity_sector_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_record` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `email` varchar(255),
        `inventor_name` varchar(255),
        `open_source` bit,
        `stars` integer,
        `title` varchar(255),
        `web_site` varchar(255),
        `activity_sector_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );

    create table `ruizbulletin` (
       `id` integer not null,
        `version` integer not null,
        `email` varchar(255),
        `favourite_films` varchar(255),
        `name` varchar(255),
        `surname` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    alter table `discussion_forum` 
       add constraint UK_bh0lucmvo3025w2dl16tt130i unique (`investment_round_id`);

    alter table `discussion_forum` 
       add constraint UK_4oo7vikia3xns935a09iiit6 unique (`investor_id`);

    alter table `discussion_forum_message` 
       add constraint UK_dtnt5j5vs1isai78v9els2f11 unique (`messages_id`);

    alter table `investment_application` 
       add constraint UK_b1dfm3w93774b32gkuqjte3uh unique (`ticker`);

    alter table `investment_round` 
       add constraint UK_408l1ohatdkkut5bkt0eu6ifs unique (`ticker`);

    alter table `investment_round_investment_application` 
       add constraint UK_nxwl9r57g0hclikdowjx0k3x6 unique (`application_id`);
create index IDXhanrt2fsn54h9gc72io7efxv1 on `spam_word` (`english_translation`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accounting_record` 
       add constraint `FK41jm4vk7runvmg5tderffrele` 
       foreign key (`bookkeeper_id`) 
       references `bookkeeper` (`id`);

    alter table `accounting_record` 
       add constraint `FKfvepcb78ijkrgbxua9w2wjnut` 
       foreign key (`round_id`) 
       references `investment_round` (`id`);

    alter table `activity` 
       add constraint `FK2iqusm3hm1rxyff6g12xp6q7x` 
       foreign key (`round_id`) 
       references `investment_round` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `banner` 
       add constraint `FKfiondfno37y5go3uetvt06bp5` 
       foreign key (`card_id`) 
       references `credit_card` (`id`);

    alter table `banner` 
       add constraint `FKdocr1jjfgwx9ef5jbf675l360` 
       foreign key (`patron_id`) 
       references `patron` (`id`);

    alter table `bookkeeper` 
       add constraint FK_krvjp9eaqyapewl2igugbo9o8 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `credit_card` 
       add constraint `FK31e9eqi896koc93q7yjs5yoox` 
       foreign key (`patron_id`) 
       references `patron` (`id`);

    alter table `discussion_forum` 
       add constraint `FKmcgrpw22g3baap51wq319v1bp` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `discussion_forum` 
       add constraint `FK8rqer1ccxm7aqd2874sr66k2p` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `discussion_forum_message` 
       add constraint `FKafe6r8il0imnloykcaegsots3` 
       foreign key (`messages_id`) 
       references `message` (`id`);

    alter table `discussion_forum_message` 
       add constraint `FKet7pg2eb44o8klp2hcvkh1my8` 
       foreign key (`discussion_forum_id`) 
       references `discussion_forum` (`id`);

    alter table `entrepreneur` 
       add constraint FK_r6tqltqvrlh1cyy8rsj5pev1q 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `investment_application` 
       add constraint `FKsjmgti3uflddgogt0clorf8lt` 
       foreign key (`investment_applied_id`) 
       references `investment_round` (`id`);

    alter table `investment_application` 
       add constraint `FK1u1q9mgfc9yhue8k5suie17ck` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `investment_round` 
       add constraint `FKkj1l8c2ftn9c65y061me6t37j` 
       foreign key (`entrepreneur_id`) 
       references `entrepreneur` (`id`);

    alter table `investment_round_investment_application` 
       add constraint `FKqs2rsd4sb5errg2uiv8tww7g3` 
       foreign key (`application_id`) 
       references `investment_application` (`id`);

    alter table `investment_round_investment_application` 
       add constraint `FKni3r2yvw2xmwmkq040ts9ln4g` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `investor` 
       add constraint FK_dcek5rr514s3rww0yy57vvnpq 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `message` 
       add constraint `FK7ju7uxmh5mdbjgrfwgoem3eqd` 
       foreign key (`forum_id`) 
       references `discussion_forum` (`id`);

    alter table `message_tags` 
       add constraint `FKk6j00y1eiyu6qe5gad8rvefed` 
       foreign key (`message_id`) 
       references `message` (`id`);

    alter table `notice_related_notices` 
       add constraint `FKqc9an4dp5k6wuis8dyx289lg2` 
       foreign key (`notice_id`) 
       references `notice` (`id`);

    alter table `patron` 
       add constraint FK_8xx5nujhuio3advxc2freyu65 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `spam_word` 
       add constraint `FKt810pcdonbdfbt4vi2vfmukgq` 
       foreign key (`spam_list_id`) 
       references `spam_list` (`id`);

    alter table `technology_record` 
       add constraint `FK8u46778j70eyi41arwj6oj830` 
       foreign key (`activity_sector_id`) 
       references `activity_sector` (`id`);

    alter table `tool_record` 
       add constraint `FK3qw0ejiyueyfj8uf2ubb642nh` 
       foreign key (`activity_sector_id`) 
       references `activity_sector` (`id`);
