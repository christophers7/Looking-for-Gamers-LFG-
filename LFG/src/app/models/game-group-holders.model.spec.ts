import { GameGroupHolders } from './game-group-holders.model';

describe('GameGroupHolders', () => {
  it('should create an instance', () => {
    expect(new GameGroupHolders(1, [])).toBeTruthy();
  });
});
