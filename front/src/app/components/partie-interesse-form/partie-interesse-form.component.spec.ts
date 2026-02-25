import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartieInteresseFormComponent } from './partie-interesse-form.component';

describe('PartieInteresseFormComponent', () => {
  let component: PartieInteresseFormComponent;
  let fixture: ComponentFixture<PartieInteresseFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PartieInteresseFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartieInteresseFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
