DROP TABLE IF EXISTS public.payments_status CASCADE;
CREATE TABLE public.payments_status
(
    id              bigint NOT NULL,
    name            varchar(50) NOT NULL UNIQUE,

    CONSTRAINT status_pkey PRIMARY KEY (id)

);

DROP TABLE IF EXISTS public.payments;
CREATE TABLE public.payments
(
    id              bigint NOT NULL,
    amount          numeric(20, 2) NOT NULL,
    reward          numeric(20, 2) NOT NULL DEFAULT 0,
    reason          varchar(100),
    created_date    timestamp NOT NULL,
    updated_date    timestamp NOT NULL,
    status_id       bigint NOT NULL DEFAULT 1,
    request_id          UUID NOT NULL,

    CONSTRAINT payments_pkey PRIMARY KEY (id),
    CONSTRAINT payments_status_fk FOREIGN KEY (status_id)
            REFERENCES public.payments_status (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);






