import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LFGMainComponent } from './lfgmain/lfgmain.component';
import { NewUserComponent } from './new-user/new-user.component';
import { ProfileComponent } from './profile/profile.component';
import { ModifyProfileComponent } from './modify-profile/modify-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LFGMainComponent,
    NewUserComponent,
    ProfileComponent,
    ModifyProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
