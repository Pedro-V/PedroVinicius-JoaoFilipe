# Castle's Rooms

## Alunos

* [Pedro Vinícius](https://github.com/Pedro-V)
* [João Filipe](https://github.com/jfasr)

## Mapa
<kbd>
  <img src = "https://user-images.githubusercontent.com/99099086/192665607-de9acf02-e3a0-4b66-ae49-35bf5145241d.png" width = "600px">
</kbd>

## Descrição do jogo

O jogo descreve a aventura do jogador enquanto ele explora o [Castelo Poxim](https://pt.wikipedia.org/wiki/Rio_Poxim) em busca de uma safira. Ao longo dessa aventura, ele entra em diversas salas, e algumas delas contêm características especiais: Monstros, armadilhas, cura e o Chefão. O jogador só consegue a Safira após derrotar o Chefão. 

Algumas observações importantes sobre o jogo:
* Os comandos de movimentação são "ir" + "leste|oeste|norte|sul". O comando de ataque é "atacar".
* O jogador tem 10 de vida e 1 de dano. Os monstros tem 3 de vida e 1 de dano. O Chefão tem 8 de vida e 2 de dano.
* O combate Jogador vs Monstro é baseado em **turnos**: O jogador ataca, podendo acertar ou errar (e se acertar, podendo dar dano crítico ou não) e, em seguida, o monstro daquela sala ataca, podendo acertar ou errar, mas sem chance de crítico.
* No porão, existe um **monstro de fogo** que, caso derrotado, concede ao jogador um feitiço de bola de fogo com 3 de dano e 100% de chance de dano.
* Um jogador só pode sair de uma sala que possui um monstro se o monstro da sala já tiver sido derrotado.
* Se o monstro de uma dada sala já foi derrotado, ele não vai spawnar mais.
* Existem duas **salas com cura**. Quando o jogador entra nela, automaticamente recebe um cura, mas apenas na primeira vez que visitou tal sala.
* O limite superior da vida do jogador é 10. Nunca ultrapassa esse limite.
* A **sala de armadilha** sempre dá dano automático no momento que um jogador entra nela, mesmo se não for a primeira visita à sala.

## Referências
* Objects First with Java: A Practical Introduction using BlueJ
