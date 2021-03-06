// Generated by view binder compiler. Do not edit!
package com.example.fds.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fds.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextInputEditText loginEtEmail;

  @NonNull
  public final TextInputEditText loginEtPassword;

  @NonNull
  public final TextView loginTvForgotPassword;

  @NonNull
  public final TextView loginTvLogin;

  @NonNull
  public final TextView signup;

  private ActivityLoginBinding(@NonNull ScrollView rootView,
      @NonNull TextInputEditText loginEtEmail, @NonNull TextInputEditText loginEtPassword,
      @NonNull TextView loginTvForgotPassword, @NonNull TextView loginTvLogin,
      @NonNull TextView signup) {
    this.rootView = rootView;
    this.loginEtEmail = loginEtEmail;
    this.loginEtPassword = loginEtPassword;
    this.loginTvForgotPassword = loginTvForgotPassword;
    this.loginTvLogin = loginTvLogin;
    this.signup = signup;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.login_etEmail;
      TextInputEditText loginEtEmail = ViewBindings.findChildViewById(rootView, id);
      if (loginEtEmail == null) {
        break missingId;
      }

      id = R.id.login_etPassword;
      TextInputEditText loginEtPassword = ViewBindings.findChildViewById(rootView, id);
      if (loginEtPassword == null) {
        break missingId;
      }

      id = R.id.login_tvForgotPassword;
      TextView loginTvForgotPassword = ViewBindings.findChildViewById(rootView, id);
      if (loginTvForgotPassword == null) {
        break missingId;
      }

      id = R.id.login_tvLogin;
      TextView loginTvLogin = ViewBindings.findChildViewById(rootView, id);
      if (loginTvLogin == null) {
        break missingId;
      }

      id = R.id.signup;
      TextView signup = ViewBindings.findChildViewById(rootView, id);
      if (signup == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ScrollView) rootView, loginEtEmail, loginEtPassword,
          loginTvForgotPassword, loginTvLogin, signup);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
