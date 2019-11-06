import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingsMentorComponent } from './trainings-mentor.component';

describe('TrainingsMentorComponent', () => {
  let component: TrainingsMentorComponent;
  let fixture: ComponentFixture<TrainingsMentorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingsMentorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingsMentorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
