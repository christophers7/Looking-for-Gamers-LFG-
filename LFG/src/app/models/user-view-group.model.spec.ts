import { UserViewGroup } from './user-view-group.model';

describe('UserViewGroup', () => {
  it('should create an instance', () => {
    expect(new UserViewGroup(1, 1, 3, 3, "description")).toBeTruthy();
  });
});
