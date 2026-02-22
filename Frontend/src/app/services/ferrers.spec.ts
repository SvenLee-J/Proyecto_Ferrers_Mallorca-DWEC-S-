import { TestBed } from '@angular/core/testing';

import { Ferrers } from './ferrers';

describe('Ferrers', () => {
  let service: Ferrers;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Ferrers);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
