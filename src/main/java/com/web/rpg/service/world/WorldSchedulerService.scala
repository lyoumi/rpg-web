package com.web.rpg.service.world

trait WorldSchedulerService {
  def changeWorldStatement(): Unit
  def changeCharactersStatements(): Unit
}
