import { TestBed } from '@angular/core/testing';

import { ShiftdataResolver } from './shiftdata.resolver';

describe('ShiftdataResolver', () => {
  let resolver: ShiftdataResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(ShiftdataResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
