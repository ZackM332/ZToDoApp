// Generated by view binder compiler. Do not edit!
package com.example.todolistapptest.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.todolistapptest.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityNewTaskBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button ButtonCancel;

  @NonNull
  public final Button ButtonCreate;

  @NonNull
  public final EditText taskName;

  @NonNull
  public final TextView title;

  private ActivityNewTaskBinding(@NonNull ConstraintLayout rootView, @NonNull Button ButtonCancel,
      @NonNull Button ButtonCreate, @NonNull EditText taskName, @NonNull TextView title) {
    this.rootView = rootView;
    this.ButtonCancel = ButtonCancel;
    this.ButtonCreate = ButtonCreate;
    this.taskName = taskName;
    this.title = title;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityNewTaskBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityNewTaskBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_new_task, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityNewTaskBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ButtonCancel;
      Button ButtonCancel = ViewBindings.findChildViewById(rootView, id);
      if (ButtonCancel == null) {
        break missingId;
      }

      id = R.id.ButtonCreate;
      Button ButtonCreate = ViewBindings.findChildViewById(rootView, id);
      if (ButtonCreate == null) {
        break missingId;
      }

      id = R.id.taskName;
      EditText taskName = ViewBindings.findChildViewById(rootView, id);
      if (taskName == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new ActivityNewTaskBinding((ConstraintLayout) rootView, ButtonCancel, ButtonCreate,
          taskName, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
