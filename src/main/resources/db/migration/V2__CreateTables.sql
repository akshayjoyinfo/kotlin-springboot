CREATE TABLE public.restaurants(
                                   id int PRIMARY KEY,
                                   blurhash VARCHAR (255),
                                   launch_date DATE NULL,
                                   longitude decimal,
                                   latitude decimal,
                                   name VARCHAR(255),
                                   online Boolean NULL,
                                   popularity decimal
)