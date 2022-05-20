package com.nevesrafael.anacosmeticos.database.dados_iniciais

import com.nevesrafael.anacosmeticos.database.CategoriaDao
import com.nevesrafael.anacosmeticos.database.FabricanteDao
import com.nevesrafael.anacosmeticos.model.Categoria
import com.nevesrafael.anacosmeticos.model.Fabricante

fun carregaCategorias(dao: CategoriaDao) {

    dao.salvar(Categoria(descricao = "Perfume masculino"))
    dao.salvar(Categoria(descricao = "Perfume feminino"))
    dao.salvar(Categoria(descricao = "Perfume infantil"))
    dao.salvar(Categoria(descricao = "Desodorante roll-on"))
    dao.salvar(Categoria(descricao = "Desodorante spray"))
    dao.salvar(Categoria(descricao = "Desodorante aerosol"))
    dao.salvar(Categoria(descricao = "Desodorante em creme"))
    dao.salvar(Categoria(descricao = "Sabonete liquido"))
    dao.salvar(Categoria(descricao = "Sabonete em barra"))
    dao.salvar(Categoria(descricao = "Sabonete infantil"))
    dao.salvar(Categoria(descricao = "Sabonete mamãe bebê"))
    dao.salvar(Categoria(descricao = "Creme para as mãos"))
    dao.salvar(Categoria(descricao = "Creme para o rosto"))
    dao.salvar(Categoria(descricao = "Creme para os pés"))
    dao.salvar(Categoria(descricao = "Creme para o cabelo"))
    dao.salvar(Categoria(descricao = "Condicionador masculino"))
    dao.salvar(Categoria(descricao = "Condicionador feminino"))
    dao.salvar(Categoria(descricao = "Condicionador infantil"))
    dao.salvar(Categoria(descricao = "Shampoo masculino"))
    dao.salvar(Categoria(descricao = "Shampoo feminino"))
    dao.salvar(Categoria(descricao = "Shampoo infantil"))
    dao.salvar(Categoria(descricao = "Shampoo mamãe bebê"))
    dao.salvar(Categoria(descricao = "Hidratante corporal"))
}

fun carregaFabricantes(dao: FabricanteDao) {

    dao.salvar(Fabricante(empresa = "Natura"))
    dao.salvar(Fabricante(empresa = "Boticario"))
    dao.salvar(Fabricante(empresa = "Avon"))
}