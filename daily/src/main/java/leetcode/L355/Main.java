package leetcode.L355;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		String[] choices = {"Twitter","postTweet","postTweet","unfollow","getNewsFeed"};
		int[][] c = {new int[]{}, new int[]{1,4},new int[]{2,5}, new int[]{1,2}, new int[]{1}};
		Twitter twitter = null;
		for (int i = 0; i < choices.length; i++) {
			String choice = choices[i];
			System.out.println(choice);
			if (choice.equals("Twitter")) {
				twitter = new Twitter();
			} else if (choice.equals("postTweet")) {
				twitter.postTweet(c[i][0], c[i][1]);
			} else if (choice.equals("getNewsFeed")) {
				twitter.getNewsFeed(c[i][0]).forEach(System.out::println);
			} else if (choice.equals("follow")){
				twitter.follow(c[i][0], c[i][1]);
			} else {
				twitter.unfollow(c[i][0], c[i][1]);
			}
		}

	}
}

class Twitter {
	// myFollower:记录我的追随者
	private Map<Integer, List<Integer>> myFollower;

	// myFollowee:记录我关注的人
	private Map<Integer, List<Integer>> myFollowee;

	private Map<Integer, List<Tweet>> postTweetMap;

	public Twitter() {
		myFollower = new HashMap<>();
		myFollowee = new HashMap<>();
		postTweetMap = new HashMap<>();
	}

	/**
	 * 首先将此条tweet加入到到当前用户中
	 * 获取当前用户的粉丝
	 * 将此条tweet加入到粉丝的列表中
	 *
	 * */
	public void postTweet(int userId, int tweetId) {
		List<Tweet> postTweetList = postTweetMap.getOrDefault(userId, new LinkedList<>());
		Tweet tweet = new Tweet(tweetId);
		postTweetList.add(tweet);
		postTweetMap.put(userId, postTweetList);
	}

	/**
	 * 获取当前用户的tweetList
	 * 考虑情况：如果当前用户没发那么多tweet
	 * */
	public List<Integer> getNewsFeed(int userId) {
		List<Tweet> allTweet = new ArrayList<>();
		allTweet.addAll(postTweetMap.getOrDefault(userId, new ArrayList<>()));
		List<Integer> followeeList = myFollowee.getOrDefault(userId, new ArrayList<>());
		for (Integer id : followeeList) {
			allTweet.addAll(postTweetMap.getOrDefault(id, new ArrayList<>()));
		}
		allTweet.sort(new Comparator<Tweet>() {
			@Override
			public int compare(Tweet o1, Tweet o2) {
				return o2.getTimeStamp().compareTo(o1.getTimeStamp());
			}
		});
		List<Integer> list = new ArrayList<>();
		int count = Math.min(allTweet.size(), 10);
		for (int i=0; i<count; i++) {
			list.add(allTweet.get(i).getTweetId());
		}
		return list;
	}

	// ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户
	public void follow(int followerId, int followeeId) {
		// follwerId添加followeeId到关注列表中
		List<Integer> myFolloweeOrDefault = myFollowee.getOrDefault(followerId, new ArrayList<>());
		if (myFolloweeOrDefault.contains((Integer) followeeId)) {
			return;
		}
		myFolloweeOrDefault.add(followeeId);
		myFollowee.put(followerId, myFolloweeOrDefault);

		// followeeId添加followerId到粉丝列表中
		List<Integer> myFollowerOrDefault = myFollower.getOrDefault(followeeId, new ArrayList<>());
		myFollowerOrDefault.add(followerId);
		myFollower.put(followeeId, myFollowerOrDefault);
	}

	//  ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
	public void unfollow(int followerId, int followeeId) {
		// 首先从followerId的关注移除followeeId
		List<Integer> followeeList = myFollowee.getOrDefault(followerId, new ArrayList<>());
		followeeList.remove((Integer) followeeId);


		// 从followeeId的粉丝中移除followerId
		List<Integer> followerList = myFollower.getOrDefault(followeeId, new ArrayList<>());
		followerList.remove((Integer) followerId);
	}
}

class Tweet {
	private Integer tweetId;

	private static Integer index = 0;

	private Integer myIndex;

	Tweet(int tweetId) {
		this.tweetId = tweetId;
		index += 1;
		this.myIndex = index;
	}

	public Integer getTweetId() {
		return this.tweetId;
	}

	public Integer getTimeStamp() {
		return this.myIndex;
	}
}