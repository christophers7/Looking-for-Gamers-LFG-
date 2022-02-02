import { Injectable } from '@angular/core';
import { Game } from '../models/game.model';
import { User } from '../models/user.model';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const SELECTED_GAME = 'sel-game';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  public saveGame(game: any): void {
    window.sessionStorage.removeItem(SELECTED_GAME);
    window.sessionStorage.setItem(SELECTED_GAME, JSON.stringify(game));
  }

  public getGame(): any {
    const game = window.sessionStorage.getItem(SELECTED_GAME);
    if (game) {
      return JSON.parse(game);
    }

    return {};
  }
}
