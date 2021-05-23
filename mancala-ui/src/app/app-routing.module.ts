import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MancalaGameComponent} from "./mancala-game/mancala-game.component";

const routes: Routes = [
  {path: '', component: MancalaGameComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
