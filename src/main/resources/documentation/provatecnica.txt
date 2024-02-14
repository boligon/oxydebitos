----------------------------------------------------------------------------
                                   Pessoa :
----------------------------------------------------------------------------


(Get) FindById() - Procura uma pessoa pelo ID

localhost:8080/api/pessoa/"IdPessoa" 

----------------------------------------------------------------------------

(Get) FindByCnpjCpf - procura pessoas pelo documento: (Por RequestParam)

localhost:8080/api/pessoa?cnpjCpf=40918305012

----------------------------------------------------------------------------
(Get) FindAll() - Retorna uma lista paginada de pessoas

localhost:8080/api/pessoa

----------------------------------------------------------------------------

(Post) save() - salva um nova pessoa

localhost:8080/api/pessoa

Body
{    
    "nome": "andre",
    "cnpjCpf": "02861218916",
    "tipoPessoa": "F"
}

----------------------------------------------------------------------------

(Put) update() - altera uma pessoa pelo Id:

localhost:8080/api/pessoa/"idPessoa"

Body
{    
    "nome": "andre",
    "cnpjCpf": "02861218916",
    "tipoPessoa": "F"
}
----------------------------------------------------------------------------

(Delete) delete() - deleta um pessoa

localhost:8080/api/pessoa/"IdPessoa"



----------------------------------------------------------------------------
                                    Débito:
----------------------------------------------------------------------------

(Get) FindById() - Procura um débito pelo ID

localhost:8080/api/debito/"IdDebito" 

----------------------------------------------------------------------------
(Get) FindAll() - Retorna uma lista paginada com todos os débitos

localhost:8080/api/debito

----------------------------------------------------------------------------
(Get) getValorTotalDebitos() - Retorna a soma de todos os débitos

localhost:8080/api/debito/valortotal

----------------------------------------------------------------------------
(Post) save() - salva um novo débito

localhost:8080/api/debito

Body
{
    "pessoa": 1,
    "dataLancamento": "2024-02-10",
    "parcelas": [
        {
            "numeroParcela": 1,
            "dataVencimento": "2024-03-10",
            "situacaoParcela": "A",
            "valorParcela": 1000.00
        },
        {
            "numeroParcela": 2,
            "dataVencimento": "2024-04-10",
            "situacaoParcela": "A",
            "valorParcela": 2000.00
        },
        {
            "numeroParcela": 3,
            "dataVencimento": "2024-05-10",
            "situacaoParcela": "A",
            "valorParcela": 3000.00
        }
    ]
}
----------------------------------------------------------------------------

(Delete) delete() - deleta um débito

localhost:8080/api/debito/"IdDebito"
----------------------------------------------------------------------------

(Post) inserirParcelaDebito - Inserir uma nova parcela no Débito

localhost:8080/api/debito/"idDebito"/inserir-parcela

Body
{
            "numeroParcela": 4,
            "dataVencimento": "2024-06-10",
            "situacaoParcela": "A",
            "valorParcela": 4000.00
}




----------------------------------------------------------------------------
                                Pagamento:
----------------------------------------------------------------------------

(Get) FindById() - Procura um pagamento pelo ID

localhost:8080/api/pagamento/"IdPagamento" 

----------------------------------------------------------------------------
(Get) FindAll() - Retorna uma lista paginada com todos os pagamentos

localhost:8080/api/pagamento

----------------------------------------------------------------------------
(Post) save() - salva um novo pagamento
localhost:8080/api/pagamento (post)

Body
      {
         "numeroPagamento":1,
         "dataPagamento":"2024-02-13",
         "itens":[
            {
               "idDebito": 13,
               "valorPago":1000.00,
               "parcela":
                   {    
                     "id":25,
                     "numeroParcela":1,
                     "dataVencimento":"2024-03-10",
                     "situacaoParcela":"A",
                     "valorParcela":1000.00
                   }
            }
         ]
      }
 

----------------------------------------------------------------------------
                                Cancelamento:
----------------------------------------------------------------------------

(Get) FindById() - Procura um cancelamento pelo ID

localhost:8080/api/pagamento/"IdCancelamento" 

----------------------------------------------------------------------------
(Get) FindAll() - Retorna uma lista paginada com todos os cancelamentos

localhost:8080/api/cancelamento

----------------------------------------------------------------------------
(Post) save() - salva um novo cancelamento
localhost:8080/api/cancelamento (post)

Body
      {
         "numeroCancelamento":1,
         "dataCancelamento":"2024-02-13",
         "motivo":"teste cancelamento",
         "itensCancelamento":[
            {
               "idDebito": 13,
               "parcela":[
                   {    
                     "id":28,
                     "numeroParcela":4,
                     "dataVencimento":"2024-08-10",
                     "situacaoParcela":"A",
                     "valorParcela":4000.00
                   }
               ],
               "valorCancelado":4000.00
            }
         ]
      }
 

----------------------------------------------------------------------------
                              Extrato de Débitos:
----------------------------------------------------------------------------

(Get) findAllByPessoa() - Retorna um extrato de débito por IdPessoa

localhost:8080/api/extrato-debito/pessoa/"idPessoa"

----------------------------------------------------------------------------
(Get) findAllByCnpjCpf() - Retorna um extrato de débito por CnpjCpf - (Por RequestParam)

localhost:8080/api/extrato-debito/pessoa?cnpjCpf=40918305012

----------------------------------------------------------------------------------------------------------------------------------------
(Get) findAllByPeriodo() - Retorna um extrato de débito por Intervalo de Data de Lançamento - (Por RequestParam)

localhost:8080/api/extrato-debito/datalancamento?dataInicial=2024-02-01&dataFinal=2024-12-31

----------------------------------------------------------------------------------------------------------------------------------------
(Get) findAllByPessoaAndPeriodo() - Retorna um extrato de débito por Intervalo de Data de Lançamento - (Por RequestParam)

localhost:8080/api/extrato-debito/pessoa_datalancamento?nome=pedro&dataInicial=2024-02-01&dataFinal=2024-12-31

----------------------------------------------------------------------------------------------------------------------------------------
(Get) findAllBySituacaoParcela() - Retorna um extrato de débito por Situacao da Parcela - (Por RequestParam)

localhost:8080/api/extrato-debito/situacaoParcela?situacao=A   (Pode ser A - Aberto, P - Pago ou C - Cancelado)



----------------------------------------------------------------------------
                              Débito Parcela:
----------------------------------------------------------------------------


(Post) alterarVencimentoParcelas - Altera o vencimento de uma parcela:

localhost:8080/api/debito/"idDebito"/altera-vencimento

Body
{
            "numeroParcela": 4,
            "dataVencimento": "2024-08-10"
}

