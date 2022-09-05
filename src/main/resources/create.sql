
    create table canaux (
       id bigint not null auto_increment,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
        description varchar(255),
        name varchar(255) not null,
        updated_at datetime(6) not null,
        primary key (id)
    ) engine=InnoDB;

    create table com_sapce (
       id bigint not null auto_increment,
        description varchar(255),
        name varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table messages (
       id bigint not null auto_increment,
        created_at_time TIME DEFAULT CURRENT_TIME not null,
        content TEXT not null,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
        updated_at datetime(6) not null,
        canal_id bigint not null,
        user_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table user_channel (
       user_id bigint not null,
        canal_id bigint not null
    ) engine=InnoDB;

    create table users (
       id bigint not null auto_increment,
        email varchar(100),
        password varchar(50),
        username varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table messages 
       add constraint FKss6t8es2xelqtj12p42p5t5x3 
       foreign key (canal_id) 
       references canaux (id);

    alter table messages 
       add constraint FKpsmh6clh3csorw43eaodlqvkn 
       foreign key (user_id) 
       references users (id);

    alter table user_channel 
       add constraint FKr6k7a4dsd7lgxu7t2yq1w7g3e 
       foreign key (canal_id) 
       references canaux (id);

    alter table user_channel 
       add constraint FKspsusldw9osygm0h5bjyafsni 
       foreign key (user_id) 
       references users (id);
