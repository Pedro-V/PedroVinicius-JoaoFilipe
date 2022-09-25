# Castle's Rooms

## Alunos

* [Pedro Vinícius](https://github.com/Pedro-V)
* [João Filipe](https://github.com/jfasr)

## Mapa

<img src = "https://user-images.githubusercontent.com/99099086/191636955-5b1b66e2-6906-4f3e-a5be-a27506602950.png" width = "600px" />

## Descrição do jogo

O jogo descreve a aventura do jogador enquanto ele explora o Castelo Poxim em busca de uma safira. Ao longo dessa aventura, ele entra em diversas salas, e algumas delas contêm características especiais: Monstros, armadilhas, cura, quiz e o Chefão. O jogador só consegue a Safira após derrotar o Chefão. Algumas observações fundamentais sobre o jogo:
* Os comandos de movimentação são "ir" + "leste|oeste|norte|sul". O comando de ataque é atacar.
* O jogador tem 10 de vida e 1 de dano. Os monstros tem 3 de vida e 1 de dano. O Chefão tem 8 de vida e 2 de dano.
* O combate jogador X monstro é baseado em turnos: O jogador ataca, podendo acertar ou errar (e se acertar, podendo dar dano crítico ou não) e, em seguida, o monstro daquela sala ataca, podendo acertar ou errar, mas sem chance de crítico.
* Um jogador só pode sair de uma sala que possui um monstro se o monstro da sala já tiver sido derrotado.
* Se o monstro de uma dada sala já foi derrotado, ele não vai spawnar mais.
* Existem duas salas com cura. Quando o jogador entra nela, automaticamente recebe um cura, mas apenas na primeira vez que visitou tal sala.
* O limite superior da vida do jogador é 10. Nunca ultrapassa esse limite.
* A sala de armadilha sempre dá dano automático no momento que um jogador entra nela, mesmo se não for a primeira visita à sala.
* A sala de quiz garante que, se o jogador acertar a pergunta, ele receberá um feitiço de bola de fogo, que causa 3 de dano ao alvo e tem 100% de chance de acerto.

## To-do (risque as feitas)

* Geral (Pegue algum e coloque no seu campo)
  
  * Implementar funcionalidade para o jogador só poder sair duma sala desde que não esteja mais em combate (Ou seja, derrotou o monstro daquela sala)
  * Implementar os atributos/caracteristicas especiais da sala como um ENUM

* João
  * Finalizar a adaptação da classe `Game`
  * ~~Botar o mapa nesse README~~

* Pedro
  * ~~Escrever testes~~
  * ~~Criar classe Monster~~
  * ~~Criar classe Player~~
  * ~~Traduzir e adaptar textos do jogo~~
  * ~~Criar case para o comando ATTACK~~
  * Mover o comportamento da classe Game pra a classe Player

## Referências
* Objects First with Java: A Practical Introduction using BlueJ
