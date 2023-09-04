CREATE TABLE arquivos (
  id INT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   data BLOB NULL,
   data_criacao datetime NULL,
   CONSTRAINT pk_arquivos PRIMARY KEY (id)
);

CREATE TABLE agentes (
  id INT AUTO_INCREMENT NOT NULL,
   codigo INT NULL,
   data datetime NULL,
   data_criacao datetime NULL,
   arquivo_id INT NOT NULL,
   CONSTRAINT pk_agentes PRIMARY KEY (id)
);

ALTER TABLE agentes ADD CONSTRAINT FK_ARQUIVOS_ON_AGENTE FOREIGN KEY (arquivo_id) REFERENCES arquivos (id);

CREATE TABLE regioes (
  id INT AUTO_INCREMENT NOT NULL,
   sigla VARCHAR(255) NULL,
   agente_id INT NOT NULL,
   CONSTRAINT pk_regioes PRIMARY KEY (id)
);

ALTER TABLE regioes ADD CONSTRAINT FK_REGIOES_ON_AGENTE FOREIGN KEY (agente_id) REFERENCES agentes (id);

CREATE TABLE tipo_valores (
  id INT AUTO_INCREMENT NOT NULL,
   tipo VARCHAR(255) NULL,
   valor DECIMAL(10,3) NULL,
   regiao_id INT NOT NULL,
   CONSTRAINT pk_tipovalores PRIMARY KEY (id)
);

ALTER TABLE tipo_valores ADD CONSTRAINT FK_TIPO_VALORES_ON_REGIAO FOREIGN KEY (regiao_id) REFERENCES regioes (id);
