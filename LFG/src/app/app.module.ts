import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LFGMainComponent } from './components/lfgmain/lfgmain.component';
import { NewUserComponent } from './components/new-user/new-user.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ModifyProfileComponent } from './components/modify-profile/modify-profile.component';

import { authInterceptorProviders } from '../_helpers/auth.interceptor';
import { GameDisplayComponent } from './components/game-select/game-display/game-display.component';
import { GameCardComponent } from './components/game-select/game-card/game-card.component';
import { ViewGameGroupsComponent } from './components/group/view-game-groups/view-game-groups.component';
import { GroupCardComponent } from './components/group/group-card/group-card.component';
import { HostViewComponent } from './components/host-group/host-view/host-view.component';
import { CreateGroupComponent } from './components/host-group/create-group/create-group.component';
import { ModifyCredentialComponent } from './components/modify-credential/modify-credential.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LFGMainComponent,
    NewUserComponent,
    ProfileComponent,
    ModifyProfileComponent,
    GameDisplayComponent,
    GameCardComponent,
    ViewGameGroupsComponent,
    GroupCardComponent,
    HostViewComponent,
    CreateGroupComponent,
    ModifyCredentialComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
platformBrowserDynamic().bootstrapModule(AppModule)