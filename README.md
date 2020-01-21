# hashcode-google 2019

## Practice Round - Pizza
* Use of __Greedy algorithm__ which cuts a given pizza slice by slice.
* It starts slicing from top left corner to bottom right corner preferring the biggest possible.
* This approach reaches a total score of __901.008__.
 
## Qualification Round - Photo album sorting problem

Given a list of photos and the tags associated with each photo, arrange the photos into
a slideshow that is as interesting as possible.

* The photos are divided up into 2 different lists: vertical and horizontal.
* The vertical photos are grouped into pairs, maximizing the combined number of tags.
* The slideshow starts with the first horizontal photo.
* All the remaining photos are scored against the current last photo in the slideshow.
* The following photo is the one that maximizes the score when combined with the previous one.
