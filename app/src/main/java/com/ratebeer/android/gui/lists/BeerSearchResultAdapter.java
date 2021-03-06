package com.ratebeer.android.gui.lists;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ratebeer.android.R;
import com.ratebeer.android.api.ImageUrls;
import com.ratebeer.android.api.model.BeerSearchResult;
import com.ratebeer.android.gui.widget.Images;

import java.util.List;

public final class BeerSearchResultAdapter extends RecyclerView.Adapter<BeerSearchResultAdapter.ViewHolder> {

	private final List<BeerSearchResult> beerSearchResults;

	public BeerSearchResultAdapter(List<BeerSearchResult> beerSearchResults) {
		this.beerSearchResults = beerSearchResults;
	}

	@Override
	public BeerSearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_beer_searched, parent, false));
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		BeerSearchResult beerSearchResult = beerSearchResults.get(position);
		holder.ratingText.setBackgroundResource(ImageUrls.getColor(position));
		holder.ratingText.setText(beerSearchResult.getOverallPercentileString());
		holder.titleText.setText(beerSearchResult.beerName);
		Images.with(holder.photoImage.getContext()).loadBeer(beerSearchResult.beerId).placeholder(android.R.color.white).fit().centerInside()
				.into(holder.photoImage);
		holder.unrateableBadge.setVisibility(beerSearchResult.unrateable ? View.VISIBLE : View.GONE);
		holder.retiredBadge.setVisibility(beerSearchResult.retired ? View.VISIBLE : View.GONE);
		holder.aliasBadge.setVisibility(beerSearchResult.alias ? View.VISIBLE : View.GONE);
		holder.ratedBadge.setVisibility(beerSearchResult.ratedByUser ? View.VISIBLE : View.GONE);
	}

	@Override
	public int getItemCount() {
		return beerSearchResults.size();
	}

	public BeerSearchResult get(int position) {
		return beerSearchResults.get(position);
	}

	static class ViewHolder extends RecyclerView.ViewHolder {

		final TextView ratingText;
		final TextView titleText;
		final ImageView photoImage;
		final View unrateableBadge;
		final View retiredBadge;
		final View aliasBadge;
		final View ratedBadge;

		public ViewHolder(View v) {
			super(v);
			ratingText = (TextView) v.findViewById(R.id.rating_text);
			titleText = (TextView) v.findViewById(R.id.name_text);
			photoImage = (ImageView) v.findViewById(R.id.photo_image);
			unrateableBadge = v.findViewById(R.id.unrateable_badge);
			retiredBadge = v.findViewById(R.id.retired_badge);
			aliasBadge = v.findViewById(R.id.alias_badge);
			ratedBadge = v.findViewById(R.id.rated_badge);
		}

	}

}
