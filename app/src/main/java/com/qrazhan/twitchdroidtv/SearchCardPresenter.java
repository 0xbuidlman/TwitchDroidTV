package com.qrazhan.twitchdroidtv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URI;

public class SearchCardPresenter extends Presenter {
    private static final String TAG = "CardPresenter";

    private static Context mContext;
    private static int CARD_WIDTH = 480;
    private static int CARD_HEIGHT = 300;
    private static int CARD_WIDTH_VERT = 272;
    private static int CARD_HEIGHT_VERT = 380;

    static class ViewHolder extends Presenter.ViewHolder {
        private Stream mMovie;
        private ImageCardView mCardView;
        private Drawable mDefaultCardImage;
        private PicassoImageCardViewTarget mImageCardViewTarget;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
            mImageCardViewTarget = new PicassoImageCardViewTarget(mCardView);
            mDefaultCardImage = mContext.getResources().getDrawable(R.drawable.movie);
        }

        public void setMovie(Stream m) {
            mMovie = m;
        }

        public Stream getMovie() {
            return mMovie;
        }

        public ImageCardView getCardView() {
            return mCardView;
        }

        protected void updateCardViewImage(URI uri, Stream stream) {
            if(stream.getVertical()){
                Picasso.with(mContext)
                        .load(uri.toString())
                        .resize(CARD_WIDTH_VERT, CARD_HEIGHT_VERT)
                        .centerCrop()
                        .error(mDefaultCardImage)
                        .placeholder(R.drawable.notfound)
                        .into(mImageCardViewTarget);

            }else {
                Picasso.with(mContext)
                        .load(uri.toString())
                        .resize(CARD_WIDTH, CARD_HEIGHT)
                        .centerCrop()
                        .error(mDefaultCardImage)
                        .placeholder(R.drawable.notfound)
                        .into(mImageCardViewTarget);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder");
        mContext = parent.getContext();

        ImageCardView cardView = new ImageCardView(mContext);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setBackgroundColor(mContext.getResources().getColor(R.color.fastlane_background));
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Stream movie = (Stream) item;
        ((ViewHolder) viewHolder).setMovie(movie);

        Log.d(TAG, "onBindViewHolder");
        if (movie.getCardImageUrl() != null) {
            ((ViewHolder) viewHolder).mCardView.setTitleText(movie.getTitle());
            if(!movie.getVertical())
                ((ViewHolder) viewHolder).mCardView.setContentText(movie.getStudio());
            if(movie.getVertical()){
                ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH_VERT, CARD_HEIGHT_VERT);
            }else {
                ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
            }
            //((ViewHolder) viewHolder).mCardView.setBadgeImage(mContext.getResources().getDrawable(
            //        R.drawable.videos_by_google_icon));
            ((ViewHolder) viewHolder).updateCardViewImage(movie.getCardImageURI(), movie);
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Log.d(TAG, "onUnbindViewHolder");
    }

    @Override
    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
        Log.d(TAG, "onViewAttachedToWindow");
    }

    public static class PicassoImageCardViewTarget implements Target {
        private ImageCardView mImageCardView;

        public PicassoImageCardViewTarget(ImageCardView mImageCardView) {
            this.mImageCardView = mImageCardView;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
            Drawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), bitmap);
            mImageCardView.setMainImage(bitmapDrawable);
        }

        @Override
        public void onBitmapFailed(Drawable drawable) {
            mImageCardView.setMainImage(drawable);
        }

        @Override
        public void onPrepareLoad(Drawable drawable) {
            // Do nothing, default_background manager has its own transitions
        }
    }

}
