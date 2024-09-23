DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'situacao_enum') THEN
        CREATE TYPE situacao_enum AS ENUM ('PENDENTE', 'PAGO', 'ATRASADO');
    END IF;
END $$;

ALTER TABLE conta
    ALTER COLUMN situacao TYPE situacao_enum
    USING situacao::situacao_enum;
