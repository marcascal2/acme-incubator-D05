
    alter table `accounting_record` 
       drop 
       foreign key `FK41jm4vk7runvmg5tderffrele`;

    alter table `accounting_record` 
       drop 
       foreign key `FKfvepcb78ijkrgbxua9w2wjnut`;

    alter table `activity` 
       drop 
       foreign key `FK2iqusm3hm1rxyff6g12xp6q7x`;

    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `banner` 
       drop 
       foreign key `FKfiondfno37y5go3uetvt06bp5`;

    alter table `banner` 
       drop 
       foreign key `FKdocr1jjfgwx9ef5jbf675l360`;

    alter table `bookkeeper` 
       drop 
       foreign key FK_krvjp9eaqyapewl2igugbo9o8;

    alter table `bookkeeper_request` 
       drop 
       foreign key `FKhdducua8c58xhfrls8oiih3j0`;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `credit_card` 
       drop 
       foreign key `FK31e9eqi896koc93q7yjs5yoox`;

    alter table `discussion_forum` 
       drop 
       foreign key `FKmcgrpw22g3baap51wq319v1bp`;

    alter table `discussion_forum_investor` 
       drop 
       foreign key `FK3r7nywnm4qywhfagth8r77m0i`;

    alter table `discussion_forum_investor` 
       drop 
       foreign key `FKm5owhlecb7x22m3y73l50p0pb`;

    alter table `entrepreneur` 
       drop 
       foreign key FK_r6tqltqvrlh1cyy8rsj5pev1q;

    alter table `investment_application` 
       drop 
       foreign key `FKsjmgti3uflddgogt0clorf8lt`;

    alter table `investment_application` 
       drop 
       foreign key `FK1u1q9mgfc9yhue8k5suie17ck`;

    alter table `investment_round` 
       drop 
       foreign key `FKkj1l8c2ftn9c65y061me6t37j`;

    alter table `investor` 
       drop 
       foreign key `FKdhwcb7642hi219n23ajpvow43`;

    alter table `investor` 
       drop 
       foreign key FK_dcek5rr514s3rww0yy57vvnpq;

    alter table `investor_discussion_forum` 
       drop 
       foreign key `FKrgsomed4msuh6btdjre6h85qf`;

    alter table `investor_discussion_forum` 
       drop 
       foreign key `FK3e7mj2mcs1kg5oiyjkjusng2x`;

    alter table `message` 
       drop 
       foreign key `FK7ju7uxmh5mdbjgrfwgoem3eqd`;

    alter table `message_tags` 
       drop 
       foreign key `FKk6j00y1eiyu6qe5gad8rvefed`;

    alter table `notice_related_notices` 
       drop 
       foreign key `FKqc9an4dp5k6wuis8dyx289lg2`;

    alter table `patron` 
       drop 
       foreign key `FKlyiwy22y6u5hmqeb4s0bmoh13`;

    alter table `patron` 
       drop 
       foreign key FK_8xx5nujhuio3advxc2freyu65;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `spam_word` 
       drop 
       foreign key `FKt810pcdonbdfbt4vi2vfmukgq`;

    alter table `technology_record` 
       drop 
       foreign key `FK8u46778j70eyi41arwj6oj830`;

    alter table `tool_record` 
       drop 
       foreign key `FK3qw0ejiyueyfj8uf2ubb642nh`;

    drop table if exists `accounting_record`;

    drop table if exists `activity`;

    drop table if exists `activity_sector`;

    drop table if exists `administrator`;

    drop table if exists `anonymous`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `bookkeeper`;

    drop table if exists `bookkeeper_request`;

    drop table if exists `casasola_bulletin`;

    drop table if exists `challenge`;

    drop table if exists `coleto_bulletin`;

    drop table if exists `consumer`;

    drop table if exists `credit_card`;

    drop table if exists `discussion_forum`;

    drop table if exists `discussion_forum_investor`;

    drop table if exists `entrepreneur`;

    drop table if exists `inquire`;

    drop table if exists `investment_application`;

    drop table if exists `investment_round`;

    drop table if exists `investor`;

    drop table if exists `investor_discussion_forum`;

    drop table if exists `message`;

    drop table if exists `message_tags`;

    drop table if exists `notice`;

    drop table if exists `notice_related_notices`;

    drop table if exists `overture`;

    drop table if exists `patron`;

    drop table if exists `provider`;

    drop table if exists `spam_list`;

    drop table if exists `spam_word`;

    drop table if exists `technology_record`;

    drop table if exists `tool_record`;

    drop table if exists `user_account`;

    drop table if exists `hibernate_sequence`;

    drop table if exists `ruizbulletin`;
