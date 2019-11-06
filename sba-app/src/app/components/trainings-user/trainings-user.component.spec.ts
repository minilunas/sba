import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingsUserComponent } from './trainings-user.component';

describe('TrainingsUserComponent', () => {
  let component: TrainingsUserComponent;
  let fixture: ComponentFixture<TrainingsUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingsUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingsUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
