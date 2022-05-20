// Generated by view binder compiler. Do not edit!
package com.example.fds.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fds.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFinalBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imview;

  @NonNull
  public final ImageView imviewcross;

  @NonNull
  public final TextView text;

  private ActivityFinalBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView imview,
      @NonNull ImageView imviewcross, @NonNull TextView text) {
    this.rootView = rootView;
    this.imview = imview;
    this.imviewcross = imviewcross;
    this.text = text;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFinalBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFinalBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_final, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFinalBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imview;
      ImageView imview = ViewBindings.findChildViewById(rootView, id);
      if (imview == null) {
        break missingId;
      }

      id = R.id.imviewcross;
      ImageView imviewcross = ViewBindings.findChildViewById(rootView, id);
      if (imviewcross == null) {
        break missingId;
      }

      id = R.id.text;
      TextView text = ViewBindings.findChildViewById(rootView, id);
      if (text == null) {
        break missingId;
      }

      return new ActivityFinalBinding((ConstraintLayout) rootView, imview, imviewcross, text);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}