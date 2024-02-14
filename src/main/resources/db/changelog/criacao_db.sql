
create table pessoa(id numeric(10) not null, nome varchar(200), cnpjcpf varchar(15), tipopessoa char(1), primary key(id));
create index idxpessoa_cpf on pessoa(cpf);
create sequence seq_pessoa_id start with 1;

create table debito(id numeric(10) not null, pessoa numeric(10) not null, datalancamento date, primary key(id));
alter table debito add constraint fkdebito_pessoa foreign key(pessoa) references pessoa(id);
create index idxdebito_pessoa on debito(pessoa);
create sequence seq_debito_id start with 1;

create table debitoparcela(id numeric(10) not null, debito numeric(10) not null, numeroparcela numeric(10), datavencimento date, situacaoparcela char(1), valorparcela numeric(15,2), primary key(id));
alter table debitoparcela add constraint fkdebitoparcela_debito foreign key(debito) references debito(id);
create index idxdebitoparcela_debito on debitoparcela(debito);
create sequence seq_debitoparcela_id start with 1;

create table cancelamento(id numeric(10) not null, numerocancelamento numeric(10), datacancelamento date, motivo varchar(200), primary key(id));
create sequence seq_cancelamento_id start with 1;

create table cancelamentoitem(id numeric(10) not null, cancelamento numeric(10) not null, debitoparcela numeric(10) not null, valorcancelado numeric(15,2), primary key(id));
alter table cancelamentoitem add constraint fkcancelamentoitem_debitoparcela foreign key(debitoparcela) references debitoparcela(id);
alter table cancelamentoitem add constraint fkcancelamentoitem_cancelamento foreign key(cancelamento) references cancelamento(id);
create index idxcancelamentoitem_debitoparcela on cancelamentoitem(debitoparcela);
create sequence seq_cancelamentoitem_id start with 1;

create table pagamento(id numeric(10) not null, numeropagamento numeric(10), datapagamento date, primary key(id));
create sequence seq_pagamento_id start with 1;

create table pagamentoitem(id numeric(10) not null, pagamento numeric(10) not null, debitoparcela numeric(10), valorpago numeric(15,2), primary key(id));
alter table pagamentoitem add constraint fkpagamentoitem_debitoparcela foreign key(debitoparcela) references debitoparcela(id);
alter table pagamentoitem add constraint fkpagamentoitem_pagamento foreign key(pagamento) references pagamento(id);
create index idxpagamentoitem_debitoparcela on pagamentoitem(debitoparcela);
create sequence seq_pagamentoitem_id start with 1;
