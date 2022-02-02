import { GroupUser } from './group-user.model';
import { Group } from './group.model';

describe('Group', () => {
  it('should create an instance', () => {
    expect(new Group(1, 1, new GroupUser("user"), 3, 3, "description")).toBeTruthy();
  });
});
