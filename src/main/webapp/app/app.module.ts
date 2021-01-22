import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MobisiteSharedModule } from 'app/shared/shared.module';
import { MobisiteCoreModule } from 'app/core/core.module';
import { MobisiteAppRoutingModule } from './app-routing.module';
import { MobisiteHomeModule } from './home/home.module';
import { MobisiteEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    MobisiteSharedModule,
    MobisiteCoreModule,
    MobisiteHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MobisiteEntityModule,
    MobisiteAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class MobisiteAppModule {}
