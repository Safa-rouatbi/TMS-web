import { TestBed } from '@angular/core/testing';

import { PartieInteresseService } from './partie-interesse.service';

describe('PartieInteresseService', () => {
  let service: PartieInteresseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PartieInteresseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
